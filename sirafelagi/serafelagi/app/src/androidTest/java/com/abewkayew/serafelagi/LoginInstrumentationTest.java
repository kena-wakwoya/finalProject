package com.abewkayew.serafelagi;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.EditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
public class LoginInstrumentationTest {

    @Rule
    public ActivityTestRule<Login> activityTestRule = new ActivityTestRule<>(Login.class);
    Instrumentation.ActivityMonitor monit  = getInstrumentation().addMonitor(Job_first_entry.class.getName(),null,false);
    Login login = null;

    @Before
    public void setUp() throws Exception {
        login = activityTestRule.getActivity();
    }
    @Test
    @SmallTest
    public void testAsIfEditTextsAreWorkingAsIntended() throws Exception {

        onView(withId(R.id.check_name)).perform(typeText("kena"),closeSoftKeyboard());

        onView(withId(R.id.password1)).perform(typeText("kenapass"), closeSoftKeyboard());

        onView(withId(R.id.userEmail)).perform(typeText("kena@gmail.com"),closeSoftKeyboard());
        onView(withId(R.id.register_new_user)).perform(click());
//        onView(withId(R.id.check_name)).check(matches(withId(R.id.check_name)));

    }



    @Test
    @SmallTest
    public void testAsIfRegisterButtonResponsesForClick() throws Exception{
    onView(withId(R.id.register_new_user)).perform(click());

    }

    @After
    public void tearDown() throws Exception {
        login = null;
    }
}