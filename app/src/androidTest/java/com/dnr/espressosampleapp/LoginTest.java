package com.dnr.espressosampleapp;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class LoginTest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(
            LoginActivity.class);

    @Test
    public void shouldShowErrorOnInvalidUserName() {
        String usernameToType = "";
        String passWord = "password";
        onView(withId(R.id.et_user_name)).perform(typeText(usernameToType));
        onView(withId(R.id.et_password)).perform(typeText(passWord));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.et_user_name)).check(matches(hasErrorText("Invalid username")));
    }

    @Test
    public void shouldShowErrorOnInvalidPassword() {
        String usernameToType = "userName";
        String passWord = "";
        onView(withId(R.id.et_user_name)).perform(typeText(usernameToType));
        onView(withId(R.id.et_password)).perform(typeText(passWord));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.et_password)).check(matches(hasErrorText("Invalid password")));

    }

    @Test
    public void shouldShowToastOnLoginSuccess()
    {
        LoginActivity activity = mActivityRule.getActivity();
        String usernameToType = "userName";
        String passWord = "password";
        onView(withId(R.id.et_user_name)).perform(typeText(usernameToType));
        onView(withId(R.id.et_password)).perform(typeText(passWord));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withText("Login successful")).
                inRoot(withDecorView(not(is(activity.getWindow().getDecorView())))).
                check(matches(isDisplayed()));
    }

    @Test
    public void shouldLaunchDashboardActivityOnLoginSuccess()
    {
        Intents.init();
        String usernameToType = "userName";
        String passWord = "password";
        onView(withId(R.id.et_user_name)).perform(typeText(usernameToType));
        onView(withId(R.id.et_password)).perform(typeText(passWord));
        onView(withId(R.id.btn_login)).perform(click());
        intended(hasComponent(DashboardActivity.class.getName()));
        Intents.release();
    }


}
