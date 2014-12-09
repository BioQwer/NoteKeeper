package com.bioqwer.serverApp.integrationTests;

import com.bioqwer.serverApp.model.User;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class UserServiceTests {

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
        User user = new User("user3@email.ru", "login1", "Passsword1");
        userService.addUser(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testSavingWithNotValidPassword() throws Exception {
        User user = new User("user4@email.ru", "login213", "");
        userService.addUser(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testSavingWithNotValidEmail() throws Exception {
        User user = new User("@email.ru", "login213", "asdasdaE123");
        userService.addUser(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void testSavingWithNotValidLogin() throws Exception {
        User user = new User("asdasd@email.ru", "", "asdasdaE123");
        userService.addUser(user);
    }

    @Test
    public void testFindByLogin() throws Exception {
        User get = userService.getByLogin("tester");
        assertEquals(get.getLogin(),"tester");
        get = userService.getByLogin("login1");
        assertEquals(get.getLogin(),"login1");
    }

    @Test
    public void testFindByEmail() throws Exception {
        User get = userService.getByEmail("user1@email.ru");
        assertEquals(get.getEmail(),"user1@email.ru");
        get = userService.getByEmail("create@qwe.er");
        assertEquals(get.getEmail(),"create@qwe.er");
    }

    @Test
    public void testFindWhereLogin() throws Exception {
        User user = new User("user3@email.ru", "login3", "Passsword1");
        userService.addUser(user);
        user = new User("user5@email.ru", "login4", "Passsword1");
        userService.addUser(user);
        System.out.println("userService = " + userService.getAll());
        System.out.println("userService.searchByUserLogin(\"login\") = " + userService.searchByUserLogin("log"));
        assertEquals(3,userService.searchByUserLogin("login").size());   //test have registry dependence
    }

    @Test
    public void testEditUser() throws Exception {
        User user = new User("testeqweqwe@email.ru", "tested", "Passsword1");
        userService.addUser(user);
        user.setEmail("asdfgh@qwe.qw");
        userService.editUser(user);
        long id = user.getUserId();
        assertEquals("asdfgh@qwe.qw",userService.getById(id).getEmail());
        user.setPassword("asdasd213W");
        userService.editUser(user);
        assertEquals("asdasd213W", userService.getById(id).getPassword());
        user.setLogin("asdfgh");
        userService.editUser(user);
        assertEquals("asdfgh",userService.getById(id).getLogin());
    }

    @After
    public void tearDown() throws Exception {
        Collection<User> collection = userService.getAll();
        for (User aCollection : collection) {
            userService.delete(aCollection.getUserId());
        }
    }
}
