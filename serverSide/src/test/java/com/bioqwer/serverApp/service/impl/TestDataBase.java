package com.bioqwer.serverApp.service.impl;

import com.bioqwer.serverApp.config.DataConfig;
import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.service.NoteService;
import com.bioqwer.serverApp.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
public class TestDataBase {

    @Autowired
    UserService userService;
    @Autowired
    NoteService noteService;
    User user;

    @Before
    public void setUp() {
        user = new User();
        user.setEmail("testDB@emaal.ru");
        user.setLogin("testDB");
        user.setPassword("testDBpassword1");
    }

    @Test
    public void complexTestDataBase() {
        userService.addUser(user);
        System.out.println("user = " + user);
        user.setEmail("@asd.ew");
        user.setLogin("tesasdB");
        user.setPassword("");
        System.out.println("user = " + user);
        Note note = new Note(user, "head" + user.getLogin(), "body" + user.getLogin());
        noteService.addNote(note);
        Note note1 = new Note(user, "head1" + user.getLogin(), "body1" + user.getLogin());
        noteService.addNote(note1);

//        userService.delete(user.getUserId());
    }

    @Test
    public void delete() {
        userService.delete(3);
    }

    @Test
    public void findSavedUserById() {
        User dbUser = userService.getById(1);
        System.out.println("dbUser = " + dbUser);
        dbUser.setEmail("ASD@ka.ru");
        dbUser.setPassword("asdS@23sda");
        userService.editUser(dbUser);
        System.out.println("dbUser = " + dbUser);
    }


}