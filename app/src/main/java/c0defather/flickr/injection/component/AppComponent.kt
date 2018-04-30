package c0defather.flickr.injection.component

import c0defather.flickr.injection.module.Bindings
import c0defather.flickr.injection.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by kuanysh on 4/28/18.
 */
@Singleton
@Component(modules = arrayOf(Bindings::class, NetworkModule::class))
interface AppComponent {

    fun activityComponent(): ActivityComponent

}