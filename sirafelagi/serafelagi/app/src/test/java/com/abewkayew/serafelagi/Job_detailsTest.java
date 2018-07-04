package com.abewkayew.serafelagi;

import android.view.Menu;
import android.view.MenuItem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Job_detailsTest {
    @Mock
    MenuItem item;

    Job_details job_details = null;
    @Before
    public void setUp() throws Exception {
        job_details = new Job_details();
    }


    @Test
    public void testonOptionsItemSelected() {
//        when(job_details.onOptionsItemSelected(item)).thenReturn(true);

        boolean bool = job_details.onOptionsItemSelected(item);
        assertEquals(true,bool);
    }
    @After
    public void tearDown() throws Exception {
        job_details = null;
    }

}