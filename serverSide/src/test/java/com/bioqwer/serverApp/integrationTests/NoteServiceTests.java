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

import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class NoteServiceTests {

    User user = new User("user1@email.ru", "login1", "Passsword1");
    Note testNote = new Note(user, "Head1", "Body1");
    User dbCreate = new User("tester2@qwe.er", "login2", "PassTest1");
    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;
    @Qualifier("noteService")
    @Autowired
    private NoteService noteService;

    @Before
    public void setUp() throws Exception {
        userService.addUser(user);
        userService.addUser(dbCreate);
        noteService.addNote(testNote);
    }

    @Test
    public void testAddNote() throws Exception {
        Note valid = new Note(user,"123","");
        noteService.addNote(valid);
    }

    @Test
    public void testEditNote() throws Exception {

    }

    @Test
    public void testDeleteNote() throws Exception {

    }

    @Test
    public void testGetById() throws Exception {

    }

    @Test
    public void testSearchByHead() throws Exception {

    }

    @Test
    public void testSearchByBody() throws Exception {

    }

    @Test
    public void testGetUser() throws Exception {

    }

    @Test
    public void testGetAllUserNotes() throws Exception {

    }

    @Test
    public void testSearchInAllParamsOfNotes() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        Collection<User> collection = userService.getAll();
        for (User aCollection : collection) {
            userService.delete(aCollection.getUserId());
        }
    }
}
