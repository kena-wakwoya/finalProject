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
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class Job_first_entryInstrumentationTest {
    @Rule
    public ActivityTestRule<Job_first_entry> actrule = new ActivityTestRule<>(Job_first_entry.class);
    Job_first_entry job_first_entry = null;
    Instrumentation.ActivityMonitor mon= getInstrumentation().addMonitor(Medicine.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        job_first_entry = actrule.getActivity();
    }
    @Test
    @SmallTest
    public void testCardViewResponseToClick() throws Exception {
        onView(withId(R.id.card_view_medicine)).perform(click());
        Activity  job_ent = getInstrumentation().waitForMonitorWithTimeout(mon,5000);
        onView(withId(R.id.profile_header_layout)).perform(click());

    }

    @After
    public void tearDown() throws Exception {
        job_first_entry = null;
    }
}