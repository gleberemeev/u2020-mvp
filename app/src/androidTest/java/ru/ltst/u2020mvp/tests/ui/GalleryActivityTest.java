package ru.ltst.u2020mvp.tests.ui;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.ltst.u2020mvp.R;
import ru.ltst.u2020mvp.tests.util.ActivityRule;
import ru.ltst.u2020mvp.tests.util.Constants;
import ru.ltst.u2020mvp.tests.util.TimerTestRule;
import ru.ltst.u2020mvp.tests.util.ViewActions;
import ru.ltst.u2020mvp.ui.gallery.GalleryActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class GalleryActivityTest {

    @Rule
    public final ActivityTestRule<GalleryActivity> main = new ActivityTestRule<>(GalleryActivity.class);
    @Rule
    public final TimerTestRule timerTestRule = new TimerTestRule();

    @Test
    public void loadData() {
        String tag = "1. Root view visible, progress bar showing";
        onView(isRoot()).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        tag = "2. Grid shown with images loaded";
        timerTestRule.scheduleTimeout(Constants.WAIT_DELAY);
        onView(withId(R.id.gallery_grid))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void listScrolling() {
        String tag = "1. Grid shown with images loaded";
        timerTestRule.scheduleTimeout(Constants.WAIT_DELAY);
        onView(withId(R.id.gallery_grid)).perform(ViewActions.swipeTop());
        tag = "2. Scrolled to bottom";
        tag = "3. Scrolled to top";
        onView(withId(R.id.gallery_grid)).perform(ViewActions.swipeBottom());
    }
}
