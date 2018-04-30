package c0defather.examplemvp.data

import c0defather.examplemvp.data.model.Photo
import c0defather.examplemvp.data.model.Size
import c0defather.examplemvp.data.remote.PhotoService
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by kuanysh on 4/28/18.
 */
@Singleton
internal class DataManagerImpl @Inject constructor(private val photoService: PhotoService) : DataManager {

    lateinit var photo: Photo

    override fun getPhotosByText(text: String, page: Int): Observable<List<Photo>> {
        return photoService
                .fetchPhotosByTag(text, page)
                .flatMap { Observable.just(it) }
    }

    override fun getPhotoSizes(photo_id: String): Observable<List<Size>> {
        return photoService
                .fetchPhotoSizes(photo_id)
                .flatMap { Observable.just(it) }
    }

    override fun setCurrentPhoto(photo: Photo) {
        this.photo = photo
    }

    override fun getCurrentPhoto(): Photo { return photo }

}