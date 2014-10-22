package com.bioqwer.serverApp.model;

import com.bioqwer.serverApp.config.DataConfig;
import com.bioqwer.serverApp.security.ValidationUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
public class UserTest {

    User user;

    @Before
    public void setUp() {

    }

    @Test
    public void testGetUserId() throws Exception {

    }

    @Test
    public void testSetUserId() throws Exception {

    }

    @Test
    public void testGetEmail() throws Exception {

    }

    @Test
    public void testSetEmail() throws Exception {
        user = new User();
        user.setEmail("eqswqe@ewe.qwe");

        ValidationUtil.isValid(user);
    }

    @Test
    public void testGetLogin() throws Exception {

    }

    @Test
    public void testSetLogin() throws Exception {

    }

    @Test
    public void testGetPassword() throws Exception {

    }

    @Test
    public void testSetPassword() throws Exception {
        user = new User();
        user.setEmail("asd");
        user.setPassword("12");

        ValidationUtil.isValid(user);

        System.out.println("ValidationUtil.isValidPassword(user) = " + ValidationUtil.isValidPassword(user));
    }

    @Test
    public void testGetNotesByUserId() throws Exception {

    }

    @Test
    public void testSetNotesByUserId() throws Exception {

    }
}