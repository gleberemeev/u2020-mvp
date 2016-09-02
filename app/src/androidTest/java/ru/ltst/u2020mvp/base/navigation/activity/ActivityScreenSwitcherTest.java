package ru.ltst.u2020mvp.base.navigation.activity;

import android.support.test.espresso.UiController;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;

import ru.ltst.u2020mvp.R;
import ru.ltst.u2020mvp.tests.base.BaseTest;
import ru.ltst.u2020mvp.ui.gallery.GalleryActivity;
import ru.ltst.u2020mvp.ui.image.ImgurImageActivity;
import ru.ltst.u2020mvp.ui.image.ImgurImageView;
import ru.ltst.u2020mvp.util.SimpleViewAction;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class ActivityScreenSwitcherTest extends BaseTest {
    private static final String TEST_IMAGE_ID = "0y3uACw";

    @Inject
    ActivityScreenSwitcher activityScreenSwitcher;

    @Rule
    public ActivityTestRule<GalleryActivity> activityTestRule = new ActivityTestRule<>(GalleryActivity.class);

    @Before
    public void setUp() throws Exception {
        getApp().getTestComponent().inject(this);
    }

    @Test
    public void open() throws Exception {
        activityScreenSwitcher.detach(activityTestRule.getActivity());
        activityScreenSwitcher.open(new ImgurImageActivity.Screen(TEST_IMAGE_ID));
        onView(withId(R.id.gallery_view))
                .check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void open2() throws Exception {
        activityScreenSwitcher.open(new ImgurImageActivity.Screen(TEST_IMAGE_ID));
        onView(withId(R.id.imgur_image_view))
                .check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void goBack() throws Exception {
        activityScreenSwitcher.open(new ImgurImageActivity.Screen(TEST_IMAGE_ID));
        onView(withId(R.id.imgur_image_view))
                .perform(new SimpleViewAction<ImgurImageView>() {
                    @Override
                    protected void call(UiController uiController, ImgurImageView view) {
                        activityScreenSwitcher.goBack();
                    }
                });
        onView(withId(R.id.gallery_view))
                .check(matches(isCompletelyDisplayed()));
    }

}