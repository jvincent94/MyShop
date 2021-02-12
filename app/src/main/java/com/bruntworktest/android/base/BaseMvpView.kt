package com.bruntworktest.android.base

import android.content.Context
import androidx.annotation.StringRes

/**
 * Created by John Vincent Fernandez on 02/12/2021.
 */
interface BaseMvpView {

    fun getContext(): Context

    fun showError(error: String?)

    fun showError(@StringRes stringResId: Int)

    fun showMessage(@StringRes srtResId: Int)

    fun showMessage(message: String)
}