package com.bruntworktest.android.module.cart

import android.util.Log
import com.bruntworktest.android.base.BaseMvpPresenterImpl
import com.bruntworktest.android.model.Cart
import com.bruntworktest.android.model.CartItem
import io.paperdb.Paper

class CartPresenter: BaseMvpPresenterImpl<CartContract.View>(), CartContract.Presenter {

    override fun init() {
        Paper.init(mView!!.getContext())
        mView!!.showCartCount(Cart.getCartSize())
    }

    override fun loadItems() {
        val cart = Cart.getCart()
        mView!!.showItems(cart)
        mView!!.showSubTotal(Cart.getSubtotal())
    }

    override fun deleteItem(cartItem: CartItem) {
        Cart.removeItem(cartItem)
        mView!!.showMessage("${cartItem.product.name} was removed from your cart")
        loadItems()
        mView!!.showCartCount(Cart.getCartSize())
    }



}