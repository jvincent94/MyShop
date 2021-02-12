package com.bruntworktest.android.base

/**
 * Created by John Vincent Fernandez on 02/12/2021.
 */
open class BaseMvpPresenterImpl<V : BaseMvpView> : BaseMvpPresenter<V> {

    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }
}