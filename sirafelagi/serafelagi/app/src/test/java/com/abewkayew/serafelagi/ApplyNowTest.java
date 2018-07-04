package com.abewkayew.serafelagi;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ApplyNowTest {
    Apply_Now_data apply_now_data = new Apply_Now_data("AAU","3.1","tempo",1);


    @Before
    public void init(){
        apply_now_data.setName_of_university("jimma");
        apply_now_data.setGpa("4.00");
        apply_now_data.setTempoFile("graduatee");
        apply_now_data.setSync_status(1);
    }
    @Test
    @SmallTest
    public void asIfReturnnameWorks() throws  Exception{
        String name = apply_now_data.getName_of_university();

        assertEquals("jimma",name);

    }
    @Test
    @SmallTest
    public void asifSetnameWorks () throws  Exception{
        apply_now_data.setName_of_university("Wallaggaa");
        String jim = apply_now_data.getName_of_university();
        assertEquals("Wallaggaa",jim);

    }
    @Test
    @SmallTest
    public void checkAsIfGetgpaWorks() throws Exception{
        String gpa = apply_now_data.getGpa();
        assertEquals("4.00",gpa);
    }
    @Test
    @SmallTest
    public void checkAsIfSeGpaWorks() throws  Exception{
        apply_now_data.setGpa("3.0");
        String gpa1 = apply_now_data.getGpa();
        assertEquals("3.0",gpa1);
    }
    @Test
    @SmallTest
    public void checkAsIfGetTempworks() throws Exception{
        String temp = apply_now_data.getTempoFile();
        assertEquals("graduatee",temp);
    }
    @Test
    @SmallTest
    public void checkAsifSysncGetWorks() throws  Exception{
        int syn = apply_now_data.getSync_status();
        assertEquals(1,syn);
    }

}
