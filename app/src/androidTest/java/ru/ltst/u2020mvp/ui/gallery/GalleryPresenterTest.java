package ru.ltst.u2020mvp.ui.gallery;

import android.support.test.espresso.UiController;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import ru.ltst.u2020mvp.R;
import ru.ltst.u2020mvp.base.BaseTest;
import ru.ltst.u2020mvp.util.Constants;
import ru.ltst.u2020mvp.util.TimerTestRule;
import ru.ltst.u2020mvp.ui.gallery.view.GalleryView;
import ru.ltst.u2020mvp.util.SimpleViewAction;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

public class GalleryPresenterTest extends BaseTest {
    private GalleryActivity.Presenter presenter;

    @Rule
    public ActivityTestRule<GalleryActivity> activityTestRule =
            new ActivityTestRule<>(GalleryActivity.class);
    @Rule
    public TimerTestRule timerTestRule = new TimerTestRule();

    @Before
    public void setUp() {
        presenter = activityTestRule.getActivity().getComponent().presenter();
    }

    @Test
    public void onLoad() throws Exception {
        timerTestRule.scheduleTimeout(Constants.WAIT_DELAY);
        //check content displayed
        onView(withId(R.id.gallery_swipe_refresh))
                .check(matches(isCompletelyDisplayed()));
        //check clicks subscribing
        onView(withId(R.id.gallery_grid))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.imgur_image_view))
                .check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void onDestroy() throws Exception {
        onView(withId(R.id.gallery_view))
                .perform(new SimpleViewAction<GalleryView>() {
                    @Override
                    protected void call(UiController uiController, GalleryView view) {
                        presenter.dropView(view);
                    }
                });
        //check no clicks
        onView(withId(R.id.gallery_grid))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.gallery_view))
                .check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void refresh() throws Exception {
        //TODO: create test after implementing refreshing behaviour
    }

}