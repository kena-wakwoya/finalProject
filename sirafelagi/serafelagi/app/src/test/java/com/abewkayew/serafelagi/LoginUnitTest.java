package com.abewkayew.serafelagi;

import android.content.SharedPreferences;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginUnitTest {
    @Mock
    SharedPreferences sp = mock(SharedPreferences.class);
    @Mock
            SharedPreferences.Editor editor = mock(SharedPreferences.Editor.class);

    Login logn ;

    @Before
    public void setUp() throws Exception {
        logn = new Login();


    }

    @Test
    public void testgetUserName() throws  Exception{
        when(sp.getString("name","class")).thenReturn("cla");
        assertEquals("cla",sp.getString("name","class"));
        verify(sp).getString("name","class");
    }
    @Test
    public void testsaveLoginDetails() throws Exception{

        when(editor.commit()).thenReturn(true);
        boolean b = editor.commit();
        assertEquals(true,b);

    }
//    @Test
//    public void testIsLogOut() throws Exception{
//        when(sp.getString("name","kena").isEmpty()).thenReturn(true);
//        assertEquals(true,sp.getString("name","kena").isEmpty());
//        verify(sp).getString("name","kena");
//
//    }


    @After
    public void tearDown() throws Exception{
        logn = null;

    }
}
