package com.bioqwer.serverApp.service.impl;

import com.bioqwer.serverApp.config.DataConfig;
import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;
    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setEmail("test@mailTest.ru");
        user.setLogin("testUser");
        user.setPassword("TestPassword1");
    }

    @Test
    public void testAddUser() throws Exception {
        userService.addUser(user);
        System.out.println("user = " + user);
    }

    @Test
    public void testGetById() throws Exception {
        User dbUser = userService.getById(6);
        Assert.assertEquals(userService.getById(dbUser.getUserId()), dbUser);
        System.out.println("dbUser = " + dbUser);
        dbUser = userService.getByLogin("bioqw");
        System.out.println("dbUser = " + dbUser);
    }

    @Test
    public void testGetByLogin() throws Exception {

    }

    @Test
    public void testGetByEmail() throws Exception {

    }

    @Test
    public void testEditUser() throws Exception {

    }

    @Test
    public void testGetAll() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }
}