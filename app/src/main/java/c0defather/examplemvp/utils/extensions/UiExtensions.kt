package c0defather.examplemvp.utils.extensions

import android.content.Context
import android.view.View

/**
 * Created by kuanysh on 4/28/18.
 */
fun Int.dpToPx(context: Context): Int = this * context.resources.displayMetrics.density.toInt()

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}
