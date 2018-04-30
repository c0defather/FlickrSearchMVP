package c0defather.examplemvp.ui.main.view

import android.content.Context
import c0defather.examplemvp.data.model.Photo
import c0defather.examplemvp.data.model.Size
import c0defather.examplemvp.injection.scope.PerActivity
import c0defather.examplemvp.ui.base.view.BaseListAdapter
import c0defather.examplemvp.ui.base.view.BaseViewHolder
import javax.inject.Inject

/**
 * Created by kuanysh on 4/28/18.
 */
@PerActivity
class MainAdapter @Inject constructor() : BaseListAdapter<Photo>() {

    override fun getListItemView(context: Context): BaseViewHolder<Photo> {
        return PhotoViewHolder(context)
    }

    fun setPhotoUrl(photo_id: String, sizes: List<Size>) {
        for (photo: Photo in items) {
            if (photo.id == photo_id)
                photo.sizes = sizes
        }
        notifyDataSetChanged()
    }

    fun isEmpty(): Boolean {
        return items.isEmpty()
    }
}