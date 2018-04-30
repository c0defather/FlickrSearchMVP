package c0defather.examplemvp.injection.component

import c0defather.examplemvp.injection.scope.PerActivity
import c0defather.examplemvp.ui.base.view.BaseActivity
import c0defather.examplemvp.ui.main.view.MainActivity
import c0defather.examplemvp.ui.photo.view.PhotoActivity
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