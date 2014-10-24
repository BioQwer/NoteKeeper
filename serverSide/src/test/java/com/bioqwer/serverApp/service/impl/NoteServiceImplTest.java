package com.bioqwer.serverApp.service.impl;

import com.bioqwer.serverApp.config.DataConfig;
import com.bioqwer.serverApp.service.NoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
public class NoteServiceImplTest {

    @Autowired
    private NoteService noteService;

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
        System.out.println("noteService.getByHead(\"a\") = " + noteService.getByHead("a"));
    }

    @Test
    public void testGetByBody() throws Exception {

    }

    @Test
    public void testGetUser() throws Exception {

    }
}