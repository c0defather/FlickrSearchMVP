package c0defather.flickr.utils.extensions

import android.content.Context
import c0defather.flickr.App
import c0defather.flickr.injection.component.AppComponent

/**
 * Created by kuanysh on 4/28/18.
 */
fun Context.getAppComponent(): AppComponent = (applicationContext as App).appComponent