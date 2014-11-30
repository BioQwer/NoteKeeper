package com.bioqwer.serverApp.integrationTests;

import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.service.UserService;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
//@ContextConfiguration(locations = {"classpath:exampleApplicationContext-persistence.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class ServicesTests {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        User user = new User("user1@email.ru", "login1", "Passsword1");
        userService.addUser(user);

        User dbCreate = new User("create@qwe.er", "tester", "PassTest1");
        userService.addUser(dbCreate);

    }

    @Test
    public void testGetAllUsers() {
        Collection<User> users = userService.getAll();
        assertEquals(users.size(), 2);
        System.out.println("users = " + users);
    }

    @Test(expected = Exception.class)
    public void testSavingWithSameEmail() throws Exception {
        User user = new User("user1@email.ru", "login2", "Passsword1");
        userService.addUser(user);
    }

    @Test(expected = Exception.class)
    public void testSavingWithSameLogin() throws Exception {
        User user = new User("user2@email.ru", "login1", "Passsword1");
        userService.addUser(user);
    }

    @Test
    public void testSavingWithNotValidPassword() throws Exception {
        User user = new User("user2@email.ru", "login2", "");
        userService.addUser(user);
    }
}
