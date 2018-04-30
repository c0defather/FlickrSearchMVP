package c0defather.examplemvp.ui.photo.view

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import c0defather.examplemvp.R
import c0defather.examplemvp.ui.base.view.BaseActivity
import c0defather.examplemvp.ui.base.view.MvpView
import c0defather.examplemvp.ui.photo.presenter.PhotoPresenter
import c0defather.examplemvp.utils.extensions.hide
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_photo.*
import java.lang.Exception
import javax.inject.Inject


/**
 * Created by kuanysh on 4/29/18.
 */
open class PhotoActivity : BaseActivity(), MvpView {
    @Inject lateinit var presenter: PhotoPresenter

    override fun getLayoutResId() = R.layout.activity_photo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent?.inject(this)

        setupImageView()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        presenter.bind(this)
    }

    private fun setupImageView() {
        Glide.with(this)
                .load(presenter.getCurrentPhoto().sizes.last().source)
                .listener(object : RequestListener<String, GlideDrawable> {
                    override fun onResourceReady(resource: GlideDrawable?, model: String?, target: Target<GlideDrawable>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        progressBar.hide()
                        return false
                    }

                    override fun onException(e: Exception?, model: String?, target: Target<GlideDrawable>?, isFirstResource: Boolean): Boolean {
                        Toast.makeText(this@PhotoActivity, "Error. ${e?.message.toString()}", Toast.LENGTH_SHORT).show()
                        finish()
                        return false
                    }

                })
                .into(image)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}