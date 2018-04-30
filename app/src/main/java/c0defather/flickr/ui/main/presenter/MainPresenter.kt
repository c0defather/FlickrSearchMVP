package c0defather.flickr.ui.main.presenter

import c0defather.flickr.data.DataManager
import c0defather.flickr.data.model.Photo
import c0defather.flickr.data.model.Size
import c0defather.flickr.injection.scope.PerActivity
import c0defather.flickr.ui.base.presenter.BasePresenter
import c0defather.flickr.ui.main.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Inject

/**
 * Created by kuanysh on 4/28/18.
 */
@PerActivity
class MainPresenter @Inject constructor(private val dataManager: DataManager) : BasePresenter<MainView>() {

    private var page: Int = 1

    fun fetchPhotosStart(text: String) {
        page = 1
        fetchPhotos(text, page)
    }

    fun fetchPhotosNext(text: String) {
        this.page += 1
        fetchPhotos(text, page)
    }
    private fun fetchPhotos(text: String, page: Int) {
        disposables.add(
                dataManager.getPhotosByText(text, page)
                        .subscribeOn(io())
                        .observeOn(mainThread())
                        .subscribe(
                                { onFetchPhotosIdsSuccess(it) },
                                { onFetchPhotosIdsError(it) })
        )
    }

    fun fetchPhotoSize(photo_id: String) {
        disposables.add(
                dataManager.getPhotoSizes(photo_id)
                        .subscribeOn(io())
                        .observeOn(mainThread())
                        .subscribe(
                                {onFetchPhotoSizesSuccess(photo_id, it)},
                                {onFetchPhotoSizesError(photo_id, it)}
                        )
        )
    }

    fun setCurrentPhoto(photo: Photo) {
        dataManager.setCurrentPhoto(photo)
    }

    private fun onFetchPhotosIdsSuccess(photos: List<Photo>) {
        view?.onFetchPhotosIdsSuccess(photos)
    }

    private fun onFetchPhotosIdsError(error: Throwable) {
        view?.onFetchPhotosIdsError(error)
    }

    private fun onFetchPhotoSizesSuccess(photo_id: String, sizes: List<Size>) {
        view?.onFetchPhotoSizesSuccess(photo_id, sizes)
    }

    private fun onFetchPhotoSizesError(photo_id: String, error: Throwable) {
        view?.onFetchPhotoSizesError(photo_id, error)
    }

}