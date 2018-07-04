package com.abewkayew.serafelagi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.v4.media.session.PlaybackStateCompat;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApplyNowFormTest {
    ApplyNowForm applyNowForm;

//    ArrayList<Apply_Now_data> list =new ArrayList();
//    ApplyNow_RecyclerAdapter adapter1 = new ApplyNow_RecyclerAdapter(list);
    View view,view1;

    public static final String Fake ="true";

    @Mock
    Network network = mock(Network.class);
    @Mock
    ApplyNow_RecyclerAdapter adapter = mock(ApplyNow_RecyclerAdapter.class);

    @Mock
    ConnectivityManager connectivityManager = mock(ConnectivityManager.class);
    @Mock
    NetworkInfo networkInfo = mock(NetworkInfo.class);



//    int getIt = adapter1.getItemCount();

    @Before
    public void init() throws  Exception{
        applyNowForm = new ApplyNowForm();
//        applyNowForm.submitApplyForm(view);
        applyNowForm.myLog("Bontu");

    }

    @Test
    @SmallTest
    public void testCurrentTimeWorking() throws Exception{
        String time = applyNowForm.getCurrentTime(2,33,44);

        assertEquals("2 : 33 : 44",time);


    }
    @Test
    @SmallTest
    public void checkArraySizeIsWorking() throws Exception{
        when(adapter.getItemCount()).thenReturn(0);
        assertEquals(adapter.getItemCount(),applyNowForm.checkSize());

    }
    @Test
    @SmallTest

    public void checkAsifConnectionsmWorks()throws Exception{
        when(connectivityManager.getActiveNetwork()).thenReturn(network);
        System.out.println(connectivityManager.getActiveNetwork());
        when(networkInfo.isConnected()).thenReturn(true);
        boolean bool = (networkInfo.isConnected());
        assertEquals(true,bool);
        verify(connectivityManager).getActiveNetwork();
    }
    @Test
    @SmallTest
    public void testAsIFMyLogWorks() throws Exception{
        String log  = "kena";
        applyNowForm.myLog(log);
        assertEquals("kena",log);


    }
}
