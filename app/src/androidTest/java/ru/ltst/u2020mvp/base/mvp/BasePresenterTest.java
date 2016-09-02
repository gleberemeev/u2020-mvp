package ru.ltst.u2020mvp.base.mvp;

import android.support.test.espresso.UiController;
import android.support.test.rule.ActivityTestRule;

import org.hamcrest.CustomTypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ru.ltst.u2020mvp.R;
import ru.ltst.u2020mvp.util.TimerTestRule;
import ru.ltst.u2020mvp.ui.gallery.GalleryActivity;
import ru.ltst.u2020mvp.ui.gallery.view.GalleryView;
import ru.ltst.u2020mvp.util.SimpleViewAction;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.junit.Assert.*;

public class BasePresenterTest {

    private GalleryActivity.Presenter presenter;

    @Rule
    public ActivityTestRule<GalleryActivity> activityTestRule =
            new ActivityTestRule<>(GalleryActivity.class);
    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Rule
    public TimerTestRule timerTestRule = new TimerTestRule();

    @Before
    public void setUp() {
        presenter = activityTestRule.getActivity().getComponent().presenter();
    }

    @Test
    public void takeView() throws Exception {
        timerTestRule.scheduleTimeout(5000);
        onView(withId(R.id.gallery_swipe_refresh))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)));
        expectedException.expect(NullPointerException.class);
        presenter.takeView(null);
    }

    @Test
    public void dropView() throws Exception {
        expectedException.expect(NullPointerException.class);
        presenter.dropView(null);
    }

    @Test
    public void dropView2() throws Exception {
        onView(withId(R.id.gallery_view))
                .perform(new SimpleViewAction<GalleryView>() {
                    @Override
                    protected void call(UiController uiController, GalleryView view) {
                        presenter.dropView(view);
                    }
                })
                .check((view, noViewFoundException) -> assertFalse(presenter.hasView()));
    }

    @Test
    public void getView() throws Exception {
        final GalleryView view = presenter.getView();
        assertThat(true, new CustomTypeSafeMatcher<Boolean>("view returns correctly") {
            @Override
            protected boolean matchesSafely(Boolean item) {
                return view != null;
            }
        });
        presenter.dropView(view);
        expectedException.expect(NullPointerException.class);
        presenter.getView();
    }
}