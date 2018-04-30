package c0defather.examplemvp.ui.base.presenter

import c0defather.examplemvp.ui.base.view.MvpView
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by kuanysh on 4/28/18.
 */
abstract class BasePresenter<T : MvpView> {

    protected val disposables = CompositeDisposable()
    protected var view: T? = null

    fun bind(view: T) {
        this.view = view
    }

    fun unbind() {
        this.view = null
    }

    fun destroy() {
        disposables.clear()
        unbind()
    }

}