package c0defather.flickr.ui.main.view

import android.content.Context
import c0defather.flickr.data.model.Photo
import c0defather.flickr.data.model.Size
import c0defather.flickr.injection.scope.PerActivity
import c0defather.flickr.ui.base.view.BaseListAdapter
import c0defather.flickr.ui.base.view.BaseViewHolder
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