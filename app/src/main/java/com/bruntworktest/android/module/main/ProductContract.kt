package com.bruntworktest.android.module.main

import com.bruntworktest.android.base.BaseMvpPresenter
import com.bruntworktest.android.base.BaseMvpView
import com.bruntworktest.android.model.CartItem
import com.bruntworktest.android.model.Product
import com.bruntworktest.android.model.Products

/**
 * Created by John Vincent Fernandez on 02/16/2021.
 */
object ProductContract {

    interface View : BaseMvpView {
        fun showProducts(products: MutableList<Product>)
        fun showCartCount(count: Int)
        fun showChips(products: MutableList<Product>)
    }

    interface Presenter: BaseMvpPresenter<View>{
        fun init()
        fun loadProducts()
        fun addProduct(cartItem: CartItem)
        fun deleteProduct(cartItem: CartItem)
    }
}