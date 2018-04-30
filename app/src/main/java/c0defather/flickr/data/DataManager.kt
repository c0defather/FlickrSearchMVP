package c0defather.flickr.data

import c0defather.flickr.data.model.Photo
import c0defather.flickr.data.model.Size
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