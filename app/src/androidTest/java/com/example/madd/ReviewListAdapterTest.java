package com.example.madd;

import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.madd.testActivities.ReviewTestActivity;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;


import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.hamcrest.Matchers.not;

/**
 * Instrumented Unit test for ListView in Review Activity. Instead of using real Review Activity,
 * here I used, fake ReviewActivity - ReviewTestActivity
 * @Author - https://github.com/mdilshan
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ReviewListAdapterTest {
    /**
     * Loading Review Activity Stub
     */
    @Rule
    public ActivityScenarioRule<ReviewTestActivity> rule = new ActivityScenarioRule<ReviewTestActivity>(ReviewTestActivity.class);

    /**
     * The delete button should visible for the reviews that I posted
     */
    @Test
    public void deleteWorks_onMyReviews() {
        onData(allOf(instanceOf(ReviewInterface.class), withContentName("You")))
                .onChildView(withId(R.id.deleteReviewBtn))
                .perform(click());
    }

    /**
     * The edit button should visible for the reviews made by myself
     */
    @Test public void editsWorks_onMyReview() {
        onData(allOf(instanceOf(ReviewInterface.class), withContentName("You")))
                .onChildView(withId(R.id.editReviewBtn))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    /**
     * The edit button should not visible in other's reviews
     */
    @Test public void editNotWorks_onOthersReview() {
        onData(allOf(instanceOf(ReviewInterface.class), withContentName("Kamal")))
                .onChildView(withId(R.id.editReviewBtn))
                .check(matches(not(isDisplayed())));
    }

    /**
     * The delete button should not visible in other's reviews
     */
    @Test
    public void deleteNotWorks_onOthersReview() {
        onData(allOf(instanceOf(ReviewInterface.class), withContentName("Kamal")))
                .onChildView(withId(R.id.deleteReviewBtn))
                .check(matches(not(isDisplayed())));
    }


    /**
     * Custom Matcher for get the raw by Author of the review
     * @param displayed_name - Name of the Author / user
     * @return
     */
    public static Matcher withContentName(final String displayed_name) {
        return new BoundedMatcher<Object, ReviewInterface>(ReviewInterface.class) {
            @Override
            protected boolean matchesSafely(ReviewInterface item) {
                return item.name.equals(displayed_name);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with id ' " + displayed_name + " '");
            }
        };
    }
}