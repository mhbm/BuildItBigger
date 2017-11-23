package com.udacity.gradle.builditbigger;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by lsitec101.macedo on 23/11/17.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ButtonClickTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    //Test about the button Tell Joke
    @Test
    public void testClick() {
        onView(withId(R.id.tell_joke_button)).check(matches(withText("Tell Joke")));
        onView(withId(R.id.tell_joke_button)).perform(click());

    }
}
