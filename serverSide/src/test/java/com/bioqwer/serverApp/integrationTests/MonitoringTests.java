package com.bioqwer.serverApp.integrationTests;

import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.service.MonitoringService;
import com.bioqwer.serverApp.service.NoteService;
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

/**
 * Created by Antony on 09.03.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceContext.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
public class MonitoringTests {

    @Qualifier("monitoringServiceImpl")
    @Autowired
    private MonitoringService monitoringService;

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Qualifier("noteServiceImpl")
    @Autowired
    private NoteService noteService;


    @Before
    public void setUp() throws Exception {
        User user = new User("user1@email.ru", "login1", "Passsword1");
        user = userService.addUser(user);
        System.out.println("user = " + user);
        System.out.println(monitoringService.addUserMonitoring(user));

        Note note = new Note(user,"Head ","Body ");
        noteService.addNote(note);
        System.out.println("addNoteMonitoring(Note(user,\"Head \",\"Body \")) = " + monitoringService.addNoteMonitoring(note));
        for (int i = 0; i < 3; i++) {
            note.setHead("Head iterations " + i);
            noteService.editNote(note);
            System.out.println("Itera " + monitoringService.addNoteMonitoring(note));
        }
        System.out.println("monitoringService = " + monitoringService.getUserActionOnNote(note));

    }

    @Test
    public void testCreate() throws Exception {


    }
}
