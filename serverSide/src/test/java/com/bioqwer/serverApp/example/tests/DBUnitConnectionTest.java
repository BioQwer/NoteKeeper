package com.bioqwer.serverApp.example.tests;

import org.dbunit.dataset.IDataSet;
import org.junit.Test;

import java.util.Arrays;

public class DBUnitConnectionTest extends AbstractDBUnitTest {


    @Test
    public void connectionTest() throws Exception {
        IDataSet testDataSet = getDataSet();
        assertNotNull(testDataSet);

        String[] tables = getConnection().createDataSet().getTableNames();
        assertTrue("Please check database configuration: connectionUrl, username, password, schema.", tables.length > 0);

        LOG.debug("--------------------------" + tables.length + "---------------------------------------");
        LOG.debug(Arrays.toString(tables));
    }

}
