package c0defather.flickr.ui.base.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout

/**
 * Created by kuanysh on 4/28/18.
 */
abstract class BaseViewHolder<T> : RelativeLayout {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        View.inflate(context, layoutResId(), this)
    }

    protected abstract fun layoutResId(): Int

    abstract fun bind(item: T)

}