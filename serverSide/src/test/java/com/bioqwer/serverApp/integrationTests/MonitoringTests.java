package com.bioqwer.serverApp.integrationTests;

import com.bioqwer.serverApp.model.Monitoring;
import com.bioqwer.serverApp.model.Note;
import com.bioqwer.serverApp.model.User;
import com.bioqwer.serverApp.service.MonitoringService;
import com.bioqwer.serverApp.service.NoteService;
import com.bioqwer.serverApp.service.UserService;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
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

import static junit.framework.TestCase.assertEquals;

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


    @Test
    public void testBasic() throws Exception {
        User user = new User("user1@email.ru", "login1", "Passsword1");
        user = userService.addUser(user);
        //System.out.println("user = " + user);
        monitoringService.addUserMonitoring(user);

        Note note = new Note(user,"Head ","Body ");
        noteService.addNote(note);
        monitoringService.addNoteMonitoring(note);
        for (int i = 0; i < 3; i++) {
            note.setHead("Head iterations " + i);
            noteService.editNote(note);
            monitoringService.addNoteMonitoring(note);
        }
        monitoringService.getUserActionOnNote(note);
        System.out.println("monitoringService = " + monitoringService.getUserActionOnNote(note).size());
    }

    @Test
    public void testRevert() throws Exception {
        User user = new User("user2@email.ru", "login1", "Passsword1");
        user = userService.addUser(user);

        Note note = new Note(user,"Head ","Body ");
        noteService.addNote(note);
        note.setHead("New Head");
        noteService.editNote(note);
        Collection<Monitoring> collection = monitoringService.getUserActionOnNote(note);
        System.out.println("Before note = " + note);
        Monitoring revert = null;
        for(Monitoring monitoring:collection)
            revert = monitoring;
        System.out.println("revert = " + revert);
        note = noteService.editNote(monitoringService.revertNoteFromMonitoring(revert));
        System.out.println("After note = " + note);
        assertEquals("Head ",note.getHead());
    }
}
