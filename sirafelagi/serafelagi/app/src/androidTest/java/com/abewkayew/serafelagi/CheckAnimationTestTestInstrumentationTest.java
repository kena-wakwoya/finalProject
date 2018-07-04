package com.abewkayew.serafelagi;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class CheckAnimationTestTestInstrumentationTest {

    @Rule
    public ActivityTestRule<CheckAnimationTest> actRule = new ActivityTestRule<>(CheckAnimationTest.class,true,false);
    CheckAnimationTest checkAnimationTest = null;
//    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(CheckSharedAnimation.class.getName(),null,false);
    @Before
    public void setUp() throws Exception {
        checkAnimationTest = actRule.getActivity();
    }
    @Test
    @SmallTest
    public void testForbuttonCanLaunchTheSecondIntent() throws Exception{
        onView(withId(R.id.job_animation_layout)).perform(click());
//        Activity shared  = getInstrumentation().waitForMonitorWithTimeout(monitor,4000);
        onView(withId(R.id.relative_layout_shared)).check(matches(isNotChecked()));






    }

    @After
    public void tearDown() throws Exception {
        checkAnimationTest = null;
    }
}