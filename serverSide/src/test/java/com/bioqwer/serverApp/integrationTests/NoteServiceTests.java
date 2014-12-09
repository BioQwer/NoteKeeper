package com.bioqwer.serverApp.integrationTests;

import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.service.NoteService;
import com.bioqwer.serverApp.service.UserService;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class NoteServiceTests {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;
    @Qualifier("noteServiceImpl")
    @Autowired
    private NoteService noteService;

    User user = new User("usqwe@email.ru", "login1", "Passsword1");
    User dbCreate = new User("tester2@qwe.er", "login2", "PassTest1");
    Note testNote = new Note(dbCreate, "Head1", "Body1");

    @Before
    public void setUp() throws Exception {
        userService.addUser(user);
        userService.addUser(dbCreate);
        noteService.addNote(testNote);
        if(noteService.getAllUserNotes(user.getUserId()).size()==0) {
            for (int i = 0; i < 5; i++) {
                noteService.addNote(new Note(user, "forHead" + i, "forBody" + (100 - i * 10)));
            }
        }
    }

    @Test
    public void testAddNote() throws Exception {
        Note valid = new Note(user,"123"," ");
        noteService.addNote(valid);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testAddNotValidNote() throws Exception {
        Note notValid = new Note(user,"","");
        noteService.addNote(notValid);
    }

    @Test
    public void testEditNote() throws Exception {
        Note valid = new Note(user,"123","");
        noteService.addNote(valid);
        valid.setHead("sqw");
        noteService.editNote(valid);
        long id = valid.getNoteId();
        assertEquals("sqw",noteService.getById(id).getHead());
        valid.setBody("123");
        noteService.editNote(valid);
        assertEquals("123",noteService.getById(id).getBody());
        assertNotEquals(noteService.getById(id).getCreationDate(),noteService.getById(id).getLastChangeDate());
    }

    @Test
    public void testSearch() throws Exception {
        testNote = new Note(user, "Head2", "Body3");
        noteService.addNote(testNote);
        System.out.println("userService.getAll() = " + userService.getAll());
        System.out.println("noteService.getAllUserNotes(user.getUserId()) = " + noteService.getAllUserNotes(user.getUserId()));
        assertEquals(6,noteService.searchByHead("ea",user.getUserId()).size());
        assertEquals(6,noteService.searchByBody("Bo",user.getUserId()).size());
    }

    @Test
    public void testGetUser() throws Exception {
        assertEquals(userService.getById(dbCreate.getUserId()).getLogin(),
                noteService.getUser(testNote.getNoteId()).getLogin());
    }

    @Test
    public void testGetAllUserNotes() throws Exception {
        this.setUp();
        System.out.println("noteService.getAllUserNotes(1).size() = " + noteService.getAllUserNotes(0).size());
        assertEquals(5,noteService.getAllUserNotes(user.getUserId()).size());
    }

    @Test
    public void testSearchInAllParamsOfNotes() throws Exception {
        Collection<User> collection = userService.getAll();
        for (User aCollection : collection) {
            System.out.println("\naCollection.getLogin() = " + aCollection.getLogin() +"  id = "+aCollection.getUserId());
            Collection<Note> notes = noteService.getAllUserNotes(aCollection.getUserId());
            System.out.println(notes);
        }
        assertEquals(5,noteService.searchInAllParamsOfNotes("ea",user.getUserId()).size());
        assertEquals(1,noteService.searchInAllParamsOfNotes("Bo",dbCreate.getUserId()).size());
        assertEquals(1,noteService.searchInAllParamsOfNotes("9",user.getUserId()).size());
    }

    @After
    public void tearDown() throws Exception {
        Collection<User> collection = userService.getAll();
        for (User aCollection : collection) {
            Collection<Note> notes = noteService.getAllUserNotes(aCollection.getUserId());
            for (Note nCollection : notes)
                noteService.deleteNote(nCollection.getNoteId());
            userService.delete(aCollection.getUserId());
        }
    }
}
