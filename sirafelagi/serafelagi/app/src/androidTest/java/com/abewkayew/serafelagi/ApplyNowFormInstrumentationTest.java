package com.abewkayew.serafelagi;

import android.app.Activity;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class ApplyNowFormInstrumentationTest {
    @Rule
    public ActivityTestRule<ApplyNowForm> activityTestRule = new ActivityTestRule<>(ApplyNowForm.class);
    ApplyNowForm app = null;



    @Before
    public void setUp() throws Exception {
        app=activityTestRule.getActivity();

    }
    @Test
    @SmallTest
    public void testMaterilaEditTex() throws  Exception{
        onView(withId(R.id.applier_name)).perform(typeText("Auu"),closeSoftKeyboard());
        onView(withId(R.id.applier_grade)).perform(typeText("3.0"),closeSoftKeyboard());
        onView(withId(R.id.tempo_file)).perform(typeText("tempo is ready"),closeSoftKeyboard());
        onView(withId(R.id.apply)).perform(click());
//        assertNotNull(app.findViewById(R.id.applier_name));

        onView(withId(R.id.applier_name)).check(matches(withText("Auu")));
    }

    @Test
    @SmallTest
    public void testForButtons() throws Exception{
        //perform the click button
        onView(withId(R.id.apply)).perform(click());
        onView(withId(R.id.apply)).check(matches(withId(R.id.apply)));
    }



    @After
    public void tearDown() throws Exception {
        app=null;
    }
}