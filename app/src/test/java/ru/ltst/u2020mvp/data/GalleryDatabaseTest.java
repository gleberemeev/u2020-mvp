package ru.ltst.u2020mvp.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import retrofit2.Response;
import retrofit2.adapter.rxjava.Result;
import ru.ltst.u2020mvp.data.api.GalleryService;
import ru.ltst.u2020mvp.data.api.mock.MockImage;
import ru.ltst.u2020mvp.data.api.model.request.Section;
import ru.ltst.u2020mvp.data.api.model.request.Sort;
import ru.ltst.u2020mvp.data.api.model.response.Gallery;
import ru.ltst.u2020mvp.data.api.model.response.Image;
import ru.ltst.u2020mvp.testutils.TestRxAndroidSchedulersHook;
import ru.ltst.u2020mvp.testutils.TestRxJavaSchedulersHook;
import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.observers.TestSubscriber;
import rx.plugins.RxJavaPlugins;
import rx.plugins.RxJavaSchedulersHook;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GalleryDatabaseTest {


    @Mock
    GalleryService service;
    @InjectMocks GalleryDatabase galleryDatabase;
    private CompositeSubscription compositeSubscription;

    @Before
    public void setUp() {
        compositeSubscription = new CompositeSubscription();
        MockImage mockImage = MockImage.AOSP;
        Image image = new Image("0", mockImage.image, mockImage.title, "description",
                640, 480, 100500100, 400, false);
        Gallery gallery = new Gallery.Builder()
                .setData(Collections.singletonList(image))
                .setSuccess(true)
                .build();
        Result<Gallery> result = Result.response(Response.success(gallery));
        when(service.listGallery(Section.HOT, Sort.VIRAL, 1))
                .thenReturn(Observable.just(result));
        RxJavaPlugins.getInstance().registerSchedulersHook(new TestRxJavaSchedulersHook());
        RxAndroidPlugins.getInstance().registerSchedulersHook(new TestRxAndroidSchedulersHook());
    }

    @After
    public void tearDown() {
        compositeSubscription.unsubscribe();
        RxJavaPlugins.getInstance().reset();
        RxAndroidPlugins.getInstance().reset();
    }

    @Test
    public void loadGallery() throws Exception {
        TestSubscriber<List<Image>> testSubscriber = new TestSubscriber<>();
        compositeSubscription.add(galleryDatabase.loadGallery(Section.HOT, testSubscriber));
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
    }
}