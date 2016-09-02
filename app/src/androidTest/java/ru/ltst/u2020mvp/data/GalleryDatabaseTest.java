package ru.ltst.u2020mvp.data;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import javax.inject.Inject;

import ru.ltst.u2020mvp.R;
import ru.ltst.u2020mvp.data.api.model.request.Section;
import ru.ltst.u2020mvp.data.api.model.response.Image;
import ru.ltst.u2020mvp.base.BaseTest;
import ru.ltst.u2020mvp.util.Constants;
import ru.ltst.u2020mvp.util.TimerTestRule;
import ru.ltst.u2020mvp.ui.image.ImgurImageActivity;
import rx.observers.TestSubscriber;
import rx.subscriptions.CompositeSubscription;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class GalleryDatabaseTest extends BaseTest {

    @Inject GalleryDatabase galleryDatabase;
    private CompositeSubscription compositeSubscription;

    @Rule
    public TimerTestRule timerTestRule = new TimerTestRule();
    @Rule
    public ActivityTestRule<ImgurImageActivity> testRule =
            new ActivityTestRule<ImgurImageActivity>(ImgurImageActivity.class) {
                @Override
                protected Intent getActivityIntent() {
                    Intent intent = new Intent(getApp(), ImgurImageActivity.class);
                    intent.putExtra("ImgurImageActivity.imageId", "0y3uACw");
                    return intent;
                }
            };

    @Before
    public void setUp() {
        getApp().getTestComponent().inject(this);
        compositeSubscription = new CompositeSubscription();
    }

    @After
    public void tearDown() {
        compositeSubscription.unsubscribe();
    }

    @Test
    public void loadGallery() throws Exception {
        TestSubscriber<List<Image>> testSubscriber = new TestSubscriber<>();
        compositeSubscription.add(galleryDatabase.loadGallery(Section.HOT, testSubscriber));
        timerTestRule.scheduleTimeout(Constants.WAIT_DELAY);
        //hack to keep Espresso wait for a timer
        onView(withId(R.id.imgur_image_view))
                .check(matches(isCompletelyDisplayed()));
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
    }

}