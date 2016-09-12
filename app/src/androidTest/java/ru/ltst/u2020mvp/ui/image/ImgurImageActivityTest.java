package ru.ltst.u2020mvp.ui.image;

import android.content.Intent;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.ltst.u2020mvp.R;
import ru.ltst.u2020mvp.base.BaseTest;
import ru.ltst.u2020mvp.util.Constants;
import ru.ltst.u2020mvp.util.ViewActions;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class ImgurImageActivityTest extends BaseTest {

    @Rule
    public final ActivityTestRule<ImgurImageActivity> main =
            new ActivityTestRule<ImgurImageActivity>(ImgurImageActivity.class, false, true) {
                @Override
                protected Intent getActivityIntent() {
                    Intent intent = new Intent(getApp(), ImgurImageActivity.class);
                    intent.putExtra("ImgurImageActivity.imageId", "0y3uACw");
                    return intent;
                }
            };

    @Test
    public void verifyImage() {
        onView(isRoot()).perform(ViewActions.waitAtLeast(Constants.WAIT_DELAY));
        onView(withId(R.id.imgur_image_view))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
}