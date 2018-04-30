package c0defather.flickr.injection.module

import c0defather.flickr.data.remote.PhotoService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


/**
 * Created by kuanysh on 4/28/18.
 */
@Module
open class NetworkModule {
    companion object {
        const val API_URL = "https://api.flickr.com/services/rest/"
        const val API_KEY = "b3909e28e48c0103fc089ab6abf12681"
        const val FLICKR = "flickr"
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides @Named(FLICKR)
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        var request = chain.request()
        val url = request.url().newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .addQueryParameter("format", "json").build()
        request = request.newBuilder().url(url).build()
        val response = chain.proceed(request)

        var body = response.body().string()
        val flickrPrefix = "jsonFlickrApi("
        body = body.trimEnd().substring(flickrPrefix.length)
        val jsonBody = JSONObject(body)
        val array = when {
            jsonBody.has("photos") -> jsonBody
                    .getJSONObject("photos")
                    .getJSONArray("photo")
            jsonBody.has("sizes") -> jsonBody
                    .getJSONObject("sizes")
                    .getJSONArray("size")
            else -> JSONArray()
        }
        val newBody = ResponseBody.create(response.body().contentType(), array.toString())
        response.newBuilder().body(newBody).build()

    }.build()

    @Provides @Named(FLICKR)
    @Singleton
    fun provideRetrofit(@Named(FLICKR) okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    open fun providePhotoService(@Named(FLICKR) retrofit: Retrofit): PhotoService = retrofit.create(PhotoService::class.java)
}