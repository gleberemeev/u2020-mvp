package ru.ltst.u2020mvp.ui.gallery;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.ltst.u2020mvp.R;
import ru.ltst.u2020mvp.util.TestRxJavaSchedulersHook;
import ru.ltst.u2020mvp.util.ViewActions;
import rx.plugins.RxJavaPlugins;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class GalleryActivityTest {

    @Rule
    public final ActivityTestRule<GalleryActivity> main = new ActivityTestRule<>(GalleryActivity.class);

    @Before
    public void setUp() {
        RxJavaPlugins.getInstance().reset();
        RxJavaPlugins.getInstance().registerSchedulersHook(new TestRxJavaSchedulersHook());
    }

    @Test
    public void loadData() {
        // "1. Root view visible, progress bar showing";
        onView(isRoot()).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        // "2. Grid shown with images loaded";
        onView(withId(R.id.gallery_grid))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void listScrolling() {
        // "1. Grid shown with images loaded";
        onView(withId(R.id.gallery_grid)).perform(ViewActions.swipeTop());
        // "2. Scrolled to bottom";
        // "3. Scrolled to top";
        onView(withId(R.id.gallery_grid)).perform(ViewActions.swipeBottom());
    }
}
