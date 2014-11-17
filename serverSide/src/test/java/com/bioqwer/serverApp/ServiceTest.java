package com.bioqwer.serverApp;

import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.service.UserService;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.persistence.PersistenceContext;
import java.util.Collection;

import static junit.framework.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseSetup("toDoData.xml")
public class ServiceTest {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Test
    public void testFind() throws Exception {
        Collection<User> userList = userService.getAll();
        assertEquals(3, userList.size());
        assertEquals("tester", userList.iterator().next().getLogin());
    }

    @Test
    public void testRemove() throws Exception {

    }
}
