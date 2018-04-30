package c0defather.examplemvp.data

import c0defather.examplemvp.data.model.Photo
import c0defather.examplemvp.data.model.Size
import io.reactivex.Observable

/**
 * Created by kuanysh on 4/28/18.
 */
interface DataManager {

    fun getPhotosByText(tag: String, page: Int): Observable<List<Photo>>
    fun getPhotoSizes(photo_id: String): Observable<List<Size>>
    fun setCurrentPhoto(photo: Photo)
    fun getCurrentPhoto(): Photo

}