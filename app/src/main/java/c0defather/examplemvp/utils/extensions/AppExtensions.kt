package c0defather.examplemvp.utils.extensions

import android.content.Context
import c0defather.examplemvp.App
import c0defather.examplemvp.injection.component.AppComponent

/**
 * Created by kuanysh on 4/28/18.
 */
fun Context.getAppComponent(): AppComponent = (applicationContext as App).appComponent