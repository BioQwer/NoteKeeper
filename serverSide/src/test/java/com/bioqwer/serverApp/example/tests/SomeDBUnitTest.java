package com.bioqwer.serverApp.example.tests;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class SomeDBUnitTest extends AbstractDBUnitTest {


    @After
    public void after() throws DatabaseUnitException, SQLException, Exception {
        DatabaseOperation.DELETE_ALL.execute(getConnection(), getDataSet());
    }

    @Before
    public void before() throws DatabaseUnitException, SQLException, Exception {
        DatabaseOperation.DELETE_ALL.execute(getConnection(), getDataSet());
    }

    @Test
    public void someTest() throws DataSetException, SQLException, Exception {


    }
}
