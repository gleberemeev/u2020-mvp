package ru.ltst.u2020mvp.data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import ru.ltst.u2020mvp.ApplicationScope;
import ru.ltst.u2020mvp.data.api.GalleryService;
import ru.ltst.u2020mvp.data.api.Results;
import ru.ltst.u2020mvp.data.api.model.request.Section;
import ru.ltst.u2020mvp.data.api.model.request.Sort;
import ru.ltst.u2020mvp.data.api.model.response.Image;
import ru.ltst.u2020mvp.data.api.transforms.GalleryToImageList;
import ru.ltst.u2020mvp.data.rx.EndObserver;
import ru.ltst.u2020mvp.util.RxTransformations;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Poor-man's in-memory cache of responses. Must be accessed on the main thread.
 */
@ApplicationScope
public class GalleryDatabase {
    private final GalleryService galleryService;

    private final Map<Section, List<Image>> galleryCache = new LinkedHashMap<>();
    private final Map<Section, PublishSubject<List<Image>>> galleryRequests = new LinkedHashMap<>();

    @Inject
    public GalleryDatabase(GalleryService galleryService) {
        this.galleryService = galleryService;
    }

    //TODO pull underlying logic into a re-usable component for debouncing and caching last value.
    public Subscription loadGallery(final Section section, Observer<List<Image>> observer) {
        List<Image> images = galleryCache.get(section);
        if (images != null) {
            // We have a cached value. Emit it immediately.
            observer.onNext(images);
        }

        PublishSubject<List<Image>> galleryRequest = galleryRequests.get(section);
        if (galleryRequest != null) {
            // There's an in-flight network request for this section already. Join it.
            return galleryRequest.subscribe(observer);
        }

        galleryRequest = PublishSubject.create();
        galleryRequests.put(section, galleryRequest);

        Subscription subscription = galleryRequest.subscribe(observer);

        galleryRequest.subscribe(new EndObserver<List<Image>>() {
            @Override
            public void onEnd() {
                galleryRequests.remove(section);
            }

            @Override
            public void onNext(List<Image> images) {
                galleryCache.put(section, images);
            }
        });

        galleryService.listGallery(section, Sort.VIRAL, 1)
                .filter(Results.isSuccess())
                .map(new GalleryToImageList())
                .flatMap(Observable::from)
                .filter(image -> {
                    return !image.is_album; // No albums.
                })
                .toList()
                .compose(RxTransformations.applySchedulers())
                .subscribe(galleryRequest);

        return subscription;
    }


}
