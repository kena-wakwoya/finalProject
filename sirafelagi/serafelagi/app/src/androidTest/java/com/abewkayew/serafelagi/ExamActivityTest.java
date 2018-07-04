package com.abewkayew.serafelagi;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(AndroidJUnit4.class)
public class ExamActivityTest {
    @Rule
    public ActivityTestRule<ExamActivity> actExamRule = new ActivityTestRule<>(ExamActivity.class);
    ExamActivity exam = null;
    @Before
    public void setUp() throws Exception {
        exam = actExamRule.getActivity();

    }
    @Test
    public void testTextView1() throws Exception{
        onView(withId(exam.findViewById(R.id.questionLeft))).check(matches(isDisplayed()));
    }
    @Test
    public void testTextView2() throws Exception{
        onView(withId(exam.findViewById(R.id.question_count))).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
    }
}