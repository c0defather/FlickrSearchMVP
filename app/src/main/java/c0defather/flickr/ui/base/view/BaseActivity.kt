package c0defather.flickr.ui.base.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import c0defather.flickr.injection.component.ActivityComponent
import c0defather.flickr.utils.extensions.getAppComponent

/**
 * Created by kuanysh on 4/28/18.
 */
abstract class BaseActivity : AppCompatActivity() {

    protected var activityComponent: ActivityComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        activityComponent = getAppComponent().activityComponent()
    }

    override fun onDestroy() {
        activityComponent = null
        super.onDestroy()
    }

    protected abstract fun getLayoutResId(): Int

}