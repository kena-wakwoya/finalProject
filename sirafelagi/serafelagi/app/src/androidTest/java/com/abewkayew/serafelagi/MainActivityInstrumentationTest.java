package com.abewkayew.serafelagi;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.Button;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static android.support.test.espresso.Espresso.onView;
@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentationTest{
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
     MainActivity activity = null;

    @Before
    public void setUp() throws Exception {
     activity =activityTestRule. getActivity();
    }
    @Test
    @SmallTest
    //this method is to check wether the second activity is lauunched or not on the botton click
    public void testOnclickButtonLaunchesLoginClass(){
        assertNotNull(activity.findViewById(R.id.nextButton));
        onView(withId(R.id.nextButton)).perform(click());
        onView(withId(R.id.nextButton)).perform(click());


    }


    @After
    public void tearDown() throws Exception {
        activity = null;
    }
}