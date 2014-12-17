package com.bioqwer.serverApp.testsOnMySql;

import com.bioqwer.serverApp.config.DataConfig;
import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.repository.UserRepository;
import com.bioqwer.serverApp.service.NoteService;
import com.bioqwer.serverApp.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolationException;
import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
public class TestDataBase {

    User user;
    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;
    @Qualifier("noteServiceImpl")
    @Autowired
    private NoteService noteService;
    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        user = new User();
        user.setEmail("testDB@emaal.ru");
        user.setLogin("testDB");
        user.setPassword("testDBpassword1");
    }

    @Test
    public void complexTestDataBase() {
        try {
            userService.addUser(user);
            System.out.println("user = " + user);
            user.setEmail("qwe@asd.ew");
            user.setLogin("tesasdB");
            user.setPassword("qweeEWeweqe12");
            System.out.println("user = " + user);
            Note note = new Note(user, "head" + user.getLogin(), "body" + user.getLogin());
            noteService.addNote(note);
            Note note1 = new Note(user, "head1" + user.getLogin(), "body1" + user.getLogin());
            noteService.addNote(note1);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
//        userService.delete(user.getUserId());
    }

    @Test
    public void delete() {
        userService.delete(1);
    }

    @Test
    public void findSavedUserById() {
        User dbUser = userService.getById(2);
        System.out.println("dbUser = " + dbUser);
        try {
            dbUser.setEmail("@.ru");
            dbUser.setPassword("3sda");
            userService.editUser(dbUser);
            System.out.println("dbUser = " + dbUser);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            System.out.println("e.getConstraintViolations() = " + e.getConstraintViolations());
        }
    }

    @Test
    public void testGetAll() throws Exception {
        System.out.println("userService.getAll() = " + userService.getAll());
        Collection<User> collection = userService.getAll();
        for (User aCollection : collection) {
            System.out.println("\naCollection.getLogin() = " + aCollection.getLogin() + "  id = " + aCollection.getUserId());
            Collection<Note> notes = noteService.getAllUserNotes(aCollection.getUserId());
            System.out.println(notes);
        }
    }

    @Test
    public void testGetNotesLike() throws Exception {
        System.out.println("noteService.getNotesWhere(\"2\") = ");
    }

    @Test
    public void testGetUsersLike() throws Exception {
        System.out.println("userService = " + userService.searchByUserLogin("D"));
    }

    @Test
    public void testGetUser() throws Exception {
        userService.getById(5);
    }

    @Test
    public void testGetAllNotes() throws Exception {
        System.out.println("noteService.getAllUserNotes(user) = " + noteService.getAllUserNotes(3));
        Note note = noteService.getById(1);
        System.out.println("note = " + note);
        note.setBody("wqeqwe");
        noteService.editNote(note);
        System.out.println("note = " + note);
    }

    @Test
    public void testCreateDatabaseData() throws Exception {

        User dbCreate = new User("creat2e@qwe.er", "tester2", "PassTest1");
        userService.addUser(dbCreate);
        System.out.println("user = " + dbCreate);
        for (int i = 0; i < 5; i++) {
            int q = i + 2013;
            Note note = new Note(dbCreate, "Some head " + i + " " + dbCreate.getLogin(),
                    q + " some body " + i + dbCreate.getLogin());
            noteService.addNote(note);
        }
    }


    @Test
    public void testRegistrableLogic() throws Exception {
        try {
            User dbCreate = new User("reg@qwe.e", "register", "PassTest1");
            userService.addUser(dbCreate);
        } catch (ConstraintViolationException e) {
            e.getConstraintViolations().toArray();
            System.out.println(e.getConstraintViolations());
            e.printStackTrace();
        }

    }

    @Test
    public void testSavingWithNotValidPassword() throws Exception {
        User user = new User("user", "login2", "");
        userRepository.save(user);
        userService.delete(user.getUserId());
    }

}