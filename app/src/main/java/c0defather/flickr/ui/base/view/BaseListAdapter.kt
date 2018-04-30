package c0defather.flickr.ui.base.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by kuanysh on 4/28/18.
 */
abstract class BaseListAdapter<T> : RecyclerView.Adapter<BaseListAdapter<T>.ViewHolder>() {

    protected var items: MutableList<T> = ArrayList()

    abstract fun getListItemView(context: Context): BaseViewHolder<T>

    fun clearItems() {
        val itemCount = items.size
        items.clear()
        notifyItemRangeRemoved(0, itemCount)
    }

    fun addItems(itemsToAdd: List<T>) {
        val size = items.size
        items.addAll(itemsToAdd)
        notifyItemRangeInserted(size, items.size)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder = ViewHolder(getListItemView(viewGroup.context))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) = viewHolder.view.bind(items[position])

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(var view: BaseViewHolder<T>) : RecyclerView.ViewHolder(view)

}