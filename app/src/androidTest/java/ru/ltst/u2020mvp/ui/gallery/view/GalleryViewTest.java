package ru.ltst.u2020mvp.ui.gallery.view;

import android.accounts.NetworkErrorException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.util.Pair;
import android.widget.ImageView;

import org.junit.Rule;
import org.junit.Test;

import ru.ltst.u2020mvp.R;
import ru.ltst.u2020mvp.data.api.model.response.Image;
import ru.ltst.u2020mvp.ui.gallery.GalleryActivity;
import ru.ltst.u2020mvp.util.SimpleViewAction;
import rx.observers.TestSubscriber;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class GalleryViewTest {
    @Rule
    public ActivityTestRule<GalleryActivity> activityTestRule = new ActivityTestRule<>(GalleryActivity.class);

    @Test
    public void observeImageClicks() throws Exception {
        TestSubscriber<Pair<Image, ImageView>> viewSubscriber = new TestSubscriber<>();
        onView(withId(R.id.gallery_view))
                .perform(new SimpleViewAction<GalleryView>() {
                    @Override
                    protected void call(UiController uiController, GalleryView view) {
                        view.observeImageClicks().subscribe(viewSubscriber);
                    }
                });
        onView(withId(R.id.gallery_grid))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        viewSubscriber.assertValueCount(1);
        viewSubscriber.assertNoErrors();
        onView(withId(R.id.imgur_image_view))
                .check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void showLoading() throws Exception {
        onView(withId(R.id.gallery_view))
                .perform(new SimpleViewAction<GalleryView>() {
                    @Override
                    protected void call(UiController uiController, GalleryView view) {
                        view.showLoading();
                    }
                });
        onView(withId(R.id.trending_loading))
                .check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void showContent() throws Exception {
        onView(withId(R.id.gallery_view))
                .perform(new SimpleViewAction<GalleryView>() {
                    @Override
                    protected void call(UiController uiController, GalleryView view) {
                        view.showContent();
                    }
                });
        onView(withId(R.id.gallery_swipe_refresh))
                .check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void showError() throws Exception {
        onView(withId(R.id.gallery_view))
                .perform(new SimpleViewAction<GalleryView>() {
                    @Override
                    protected void call(UiController uiController, GalleryView view) {
                        view.showError(new NetworkErrorException());
                    }
                });
        onView(withId(R.id.trending_error))
                .check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void showEmpty() throws Exception {
        onView(withId(R.id.gallery_view))
                .perform(new SimpleViewAction<GalleryView>() {
                    @Override
                    protected void call(UiController uiController, GalleryView view) {
                        view.showEmpty();
                    }
                });
        onView(withId(R.id.trending_empty))
                .check(matches(isCompletelyDisplayed()));
    }
}