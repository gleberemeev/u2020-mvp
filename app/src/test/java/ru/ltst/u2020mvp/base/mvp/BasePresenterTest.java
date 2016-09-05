package ru.ltst.u2020mvp.base.mvp;

import org.hamcrest.CustomTypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ru.ltst.u2020mvp.base.navigation.activity.ActivityScreenSwitcher;
import ru.ltst.u2020mvp.data.GalleryDatabase;
import ru.ltst.u2020mvp.ui.gallery.GalleryActivity;
import ru.ltst.u2020mvp.ui.gallery.view.GalleryView;
import rx.Observable;
import rx.Subscription;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasePresenterTest {

    @Mock
    GalleryDatabase galleryDatabase;
    @Mock
    ActivityScreenSwitcher activityScreenSwitcher;
    @Mock
    GalleryView galleryView;
    @InjectMocks
    GalleryActivity.Presenter presenter;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        when(galleryView.observeImageClicks())
                .thenReturn(Observable.empty());
        when(galleryDatabase.loadGallery(any(), any()))
                .thenReturn(new Subscription() {
                    @Override
                    public void unsubscribe() {
                        
                    }

                    @Override
                    public boolean isUnsubscribed() {
                        return false;
                    }
                });
        presenter.takeView(galleryView);
    }

    @Test
    public void takeView() throws Exception {
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
        presenter.dropView(galleryView);
        assertFalse(presenter.hasView());
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