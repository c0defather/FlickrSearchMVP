package c0defather.flickr.injection.module

import android.util.Log
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
import javax.inject.Singleton


/**
 * Created by kuanysh on 4/28/18.
 */
@Module
open class NetworkModule {
    companion object {
        const val API_URL = "https://api.flickr.com/services/rest/"
        const val API_KEY = "b3909e28e48c0103fc089ab6abf12681"
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
        var request = chain.request()
        val url = request.url().newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .addQueryParameter("format", "json").build()
        request = request.newBuilder().url(url).build()
        val response = chain.proceed(request)
        var body = response.body().string()
        body = body.trimEnd().substring("jsonFlickrApi(".length)
        val jsonBody = JSONObject(body)
        var array : JSONArray
        array = if (jsonBody.has("photos"))
            JSONObject(body)
                    .getJSONObject("photos")
                    .getJSONArray("photo")
        else
            JSONObject(body)
                    .getJSONObject("sizes")
                    .getJSONArray("size")
        val newBody = ResponseBody.create(response.body().contentType(), array.toString())
        response.newBuilder().body(newBody).build()
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    open fun providePhotoService(retrofit: Retrofit): PhotoService = retrofit.create(PhotoService::class.java)
}