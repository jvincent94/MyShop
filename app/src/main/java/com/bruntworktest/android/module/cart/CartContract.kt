package com.bruntworktest.android.module.cart

import com.bruntworktest.android.base.BaseMvpPresenter
import com.bruntworktest.android.base.BaseMvpView
import com.bruntworktest.android.model.CartItem

object CartContract {

    interface View: BaseMvpView {
        fun showItems(cartItems: MutableList<CartItem>)
        fun showCartCount(count: Int)
        fun showSubTotal(subTotal: Double)

    }

    interface Presenter: BaseMvpPresenter<View>{
        fun init()
        fun loadItems()
        fun deleteItem(cartItem: CartItem)
    }

}