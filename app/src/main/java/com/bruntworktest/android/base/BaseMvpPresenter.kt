package com.bruntworktest.android.base

/**
 * Created by John Vincent Fernandez on 02/12/2021.
 */
interface BaseMvpPresenter<in V : BaseMvpView> {
    fun attachView(view: V)

    fun detachView()
}