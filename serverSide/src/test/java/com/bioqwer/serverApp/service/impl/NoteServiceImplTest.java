package com.bioqwer.serverApp.service.impl;

import com.bioqwer.serverApp.config.DataConfig;
import com.bioqwer.serverApp.service.NoteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = DataConfig.class)
@RunWith(MockitoJUnitRunner.class)
public class NoteServiceImplTest {

    @Qualifier("noteService")
    @Mock
    @InjectMocks
    @Autowired
    private NoteService noteServiceMock;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddNote() throws Exception {

    }

    @Test
    public void testEditNote() throws Exception {

    }

    @Test
    public void testDeleteNote() throws Exception {

    }

    @Test
    public void testGetById() throws Exception {

    }

    @Test
    public void testGetByHead() throws Exception {

    }

    @Test
    public void testGetByBody() throws Exception {

    }

    @Test
    public void testGetUser() throws Exception {

    }

    @Test
    public void testGetAllUserNotes() throws Exception {

    }

    @Test
    public void testGetNotesWhere() throws Exception {

    }
}