package ru.ltst.u2020mvp.base;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import ru.ltst.u2020mvp.ui.gallery.GalleryActivity;
import ru.ltst.u2020mvp.ui.gallery.GalleryComponent;

import static org.junit.Assert.*;

public class ComponentFinderTest {
    @Rule
    public ActivityTestRule<GalleryActivity> activityTestRule = new ActivityTestRule<>(GalleryActivity.class);

    @Test
    public void findActivityComponent() throws Exception {
        assertTrue(ComponentFinder.findActivityComponent(activityTestRule.getActivity()) instanceof GalleryComponent);
    }

}