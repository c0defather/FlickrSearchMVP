package c0defather.examplemvp.data.remote

import c0defather.examplemvp.data.model.Photo
import c0defather.examplemvp.data.model.Size
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by kuanysh on 4/28/18.
 */
interface PhotoService {

    @GET("?method=flickr.photos.search&format=json&per_page=15")
    fun fetchPhotosByTag(@Query("text") text: String, @Query("page") page: Int): Observable<List<Photo>>

    @GET("?method=flickr.photos.getSizes")
    fun fetchPhotoSizes(@Query("photo_id") id: String): Observable<List<Size>>
}