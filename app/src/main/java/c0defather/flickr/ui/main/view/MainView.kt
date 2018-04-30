package c0defather.flickr.ui.main.view

import c0defather.flickr.data.model.Photo
import c0defather.flickr.data.model.Size
import c0defather.flickr.ui.base.view.MvpView

/**
 * Created by kuanysh on 4/28/18.
 */
interface MainView : MvpView {

    fun onFetchPhotosIdsSuccess(photos: List<Photo>)

    fun onFetchPhotosIdsError(error: Throwable)

    fun onFetchPhotoSizesSuccess(photo_id: String, sizes: List<Size>)

    fun onFetchPhotoSizesError(photo_id:String, error: Throwable)

    fun setCurrentPhoto(photo: Photo)

}