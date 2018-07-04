package com.abewkayew.serafelagi;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Apply_Now_RecyclerViewTest {
    @Mock
    ArrayList<Apply_Now_data> data = new ArrayList<>();
    int postion;
    @Mock
    ApplyNow_RecyclerAdapter.MyViewHolder holder = mock(ApplyNow_RecyclerAdapter.MyViewHolder.class);

    ApplyNow_RecyclerAdapter applyNow_recyclerAdapter =  null;
    public void setup()throws Exception{
        applyNow_recyclerAdapter = new ApplyNow_RecyclerAdapter(data);

    }
    @Test
    @SmallTest
    public void testAsifItemCountWorks () throws Exception{
        int large = applyNow_recyclerAdapter.getItemCount();
        assertEquals(0,large);

    }


    @After
    public void tearDown()  throws Exception{
        applyNow_recyclerAdapter = null;
    }
}
