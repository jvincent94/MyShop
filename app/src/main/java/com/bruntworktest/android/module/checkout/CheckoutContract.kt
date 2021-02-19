package com.bruntworktest.android.module.checkout

import com.bruntworktest.android.base.BaseMvpPresenter
import com.bruntworktest.android.base.BaseMvpView
import com.bruntworktest.android.model.CartItem
import com.bruntworktest.android.model.Product

/**
 * Created by John Vincent Fernandez on 02/16/2021.
 */
object CheckoutContract {
    interface View : BaseMvpView {
        fun showSubtotal(total: Double)
        fun showCartCount(count: Int)
        fun orderSuccess(orderId: Int)
    }

    interface Presenter: BaseMvpPresenter<View> {
        fun init()
        fun checkout(name: String, email: String)
    }
}