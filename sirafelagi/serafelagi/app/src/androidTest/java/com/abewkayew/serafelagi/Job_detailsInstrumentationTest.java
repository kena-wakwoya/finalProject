package com.abewkayew.serafelagi;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class Job_detailsInstrumentationTest {
    @Rule
    public ActivityTestRule<Job_details> actrule = new ActivityTestRule<>(Job_details.class);
    Job_details job = null;
    Instrumentation.ActivityMonitor monitor  = getInstrumentation().addMonitor(ApplyNowForm.class.getName(),null,false);



    @Before
    public void setUp() throws Exception {
        job = actrule.getActivity();
    }
    @Test
    @SmallTest
    public void testTheButtonClickLaunchesTheIntent() throws  Exception{
        onView(withId(R.id.applyJob)).perform(click());


    }
    @Test
    @SmallTest
    public void testTheJobDetailTitle() throws Exception {
        onView(withId(R.id.job_title_details)).check(matches(isDisplayed()));
    }
    @Test
    @SmallTest
    public void testTheJobDetailDesc() throws Exception {
        onView(withId(R.id.job_desc_details)).check(matches(isDisplayed()));
    }
    @Test
    @SmallTest
    public void  testTheJobDetailDate() throws Exception {
        onView(withId(R.id.job_date_details)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
        job = null;
    }
}