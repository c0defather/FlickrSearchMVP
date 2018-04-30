package c0defather.examplemvp.ui.main.view

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import c0defather.examplemvp.R
import c0defather.examplemvp.data.model.Photo
import c0defather.examplemvp.ui.base.view.BaseViewHolder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_photo.view.*


/**
 * Created by kuanysh on 4/28/18.
 */
class PhotoViewHolder(context: Context) : BaseViewHolder<Photo>(context) {

    override fun layoutResId(): Int = R.layout.view_photo

    override fun bind(item: Photo) {
        if (item.sizes != null){
            Glide.with(context)
                    .load(item.sizes.first().source)
                    .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.lightGray)))
                    .into(iv_photo)
        }
        rootView.setOnClickListener({
            val act = context as MainActivity
            act.setCurrentPhoto(item)
        })
    }

}