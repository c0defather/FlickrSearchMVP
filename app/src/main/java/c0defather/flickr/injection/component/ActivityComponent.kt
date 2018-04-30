package c0defather.flickr.injection.component

import c0defather.flickr.injection.scope.PerActivity
import c0defather.flickr.ui.main.view.MainActivity
import c0defather.flickr.ui.photo.view.PhotoActivity
import dagger.Subcomponent

/**
 * Created by kuanysh on 4/28/18.
 */
@PerActivity
@Subcomponent
interface ActivityComponent {

    fun inject(activity: MainActivity)
    fun inject(activity: PhotoActivity)

}