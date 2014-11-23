package com.bioqwer.serverApp.example.tests;

import com.bioqwer.serverApp.example.TestDataConfigTest;
import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.PersistenceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataConfigTest.class})
@ActiveProfiles("test")
public class SpringTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;


    @Test
    public void addPersonTest() {
        User user = new User("ASDASD@ASD.ER", "ASD", "asdDDSAD123213");
        userService.addUser(user);

        //Assert.assertEquals(2, countRowsInTable(Person.class.getSimpleName()));
    }

    // impossible to insert two persons with the same id
    @Test(expected = PersistenceException.class)
    public void addPersonThrowsExceptionTest() {

    }

    @Test
    public void addPersonWithFather() {

    }
}
