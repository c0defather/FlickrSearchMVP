package c0defather.examplemvp

import android.app.Application
import c0defather.examplemvp.injection.component.AppComponent
import c0defather.examplemvp.injection.component.DaggerAppComponent

/**
 * Created by kuanysh on 4/28/18.
 */
class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        setupComponent()
    }

    private fun setupComponent() {
        appComponent = DaggerAppComponent.builder().build()
    }
}