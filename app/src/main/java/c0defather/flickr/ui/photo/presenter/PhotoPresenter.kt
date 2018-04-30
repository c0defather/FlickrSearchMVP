package c0defather.flickr.ui.photo.presenter

import c0defather.flickr.data.DataManager
import c0defather.flickr.data.model.Photo
import c0defather.flickr.injection.scope.PerActivity
import c0defather.flickr.ui.base.presenter.BasePresenter
import c0defather.flickr.ui.base.view.MvpView
import javax.inject.Inject

/**
 * Created by kuanysh on 4/29/18.
 */
@PerActivity
class PhotoPresenter @Inject constructor(private val dataManager: DataManager) : BasePresenter<MvpView>() {
    fun getCurrentPhoto(): Photo {
        return dataManager.getCurrentPhoto()
    }
}