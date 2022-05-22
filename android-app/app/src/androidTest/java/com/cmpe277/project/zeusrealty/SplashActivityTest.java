package com.cmpe277.project.zeusrealty;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import com.cmpe277.project.zeusrealty.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SplashActivityTest {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION",
"android.permission.ACCESS_COARSE_LOCATION");

    @Test
    public void splashActivityTest() {
        ViewInteraction frameLayout = onView(
allOf(withId(R.id.sign_in_button),
withParent(allOf(withId(R.id.container),
withParent(withId(android.R.id.content)))),
isDisplayed()));
        frameLayout.check(matches(isDisplayed()));
        
        ViewInteraction frameLayout2 = onView(
allOf(withId(R.id.sign_in_button),
withParent(allOf(withId(R.id.container),
withParent(withId(android.R.id.content)))),
isDisplayed()));
        frameLayout2.check(matches(isDisplayed()));
        
        ViewInteraction ha = onView(
allOf(withText("Sign In"),
childAtPosition(
allOf(withId(R.id.sign_in_button),
childAtPosition(
withId(R.id.container),
0)),
0),
isDisplayed()));
        ha.perform(click());
        
        ViewInteraction frameLayout3 = onView(
allOf(withId(R.id.nav_view),
withParent(allOf(withId(R.id.container),
withParent(withId(android.R.id.content)))),
isDisplayed()));
        frameLayout3.check(matches(isDisplayed()));
        
        ViewInteraction bottomNavigationItemView = onView(
allOf(withId(R.id.navigation_map), withContentDescription("Map"),
childAtPosition(
childAtPosition(
withId(R.id.nav_view),
0),
1),
isDisplayed()));
        bottomNavigationItemView.perform(click());
        
        ViewInteraction bottomNavigationItemView2 = onView(
allOf(withId(R.id.navigation_nfc), withContentDescription("NFC"),
childAtPosition(
childAtPosition(
withId(R.id.nav_view),
0),
2),
isDisplayed()));
        bottomNavigationItemView2.perform(click());
        
        ViewInteraction bottomNavigationItemView3 = onView(
allOf(withId(R.id.navigation_notifications), withContentDescription("Profile"),
childAtPosition(
childAtPosition(
withId(R.id.nav_view),
0),
3),
isDisplayed()));
        bottomNavigationItemView3.perform(click());
        
        ViewInteraction button = onView(
allOf(withId(R.id.button), withText("LOGOUT"),
withParent(withParent(withId(R.id.nav_host_fragment_activity_main))),
isDisplayed()));
        button.check(matches(isDisplayed()));
        
        ViewInteraction bottomNavigationItemView4 = onView(
allOf(withId(R.id.navigation_map), withContentDescription("Map"),
childAtPosition(
childAtPosition(
withId(R.id.nav_view),
0),
1),
isDisplayed()));
        bottomNavigationItemView4.perform(click());
        
        ViewInteraction bottomNavigationItemView5 = onView(
allOf(withId(R.id.navigation_map), withContentDescription("Map"),
childAtPosition(
childAtPosition(
withId(R.id.nav_view),
0),
1),
isDisplayed()));
        bottomNavigationItemView5.perform(click());
        
        ViewInteraction bottomNavigationItemView6 = onView(
allOf(withId(R.id.navigation_map), withContentDescription("Map"),
childAtPosition(
childAtPosition(
withId(R.id.nav_view),
0),
1),
isDisplayed()));
        bottomNavigationItemView6.perform(click());
        }
    
    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
