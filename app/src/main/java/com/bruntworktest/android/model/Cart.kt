package com.bruntworktest.android.model

import android.content.Context
import io.paperdb.Paper

/**
 * Created by John Vincent Fernandez on 02/16/2021.
 */
class Cart {
    companion object{

        fun addItem(cartItem: CartItem, context: Context) {
            val cart = Cart.getCart()

            val item = cart.singleOrNull { it.product.id == cartItem.product.id }
            if (item == null) {
                cartItem.qty++
                cart.add(cartItem)
            } else {
                item.qty++
            }

            Cart.saveCart(cart)
        }

        fun removeItem(cartItem: CartItem, context: Context) {
            val cart = Cart.getCart()

            val item = cart.singleOrNull { it.product.id == cartItem.product.id }
            if (item != null) {
                if(item.qty > 0){
                    item.qty--
                } else {
                    cart.remove(item)
                }
            }
            Cart.saveCart(cart)
        }

        fun getCart(): MutableList<CartItem> {
            return Paper.book().read("cart", mutableListOf())
        }

        fun saveCart(cart: MutableList<CartItem>) {
            Paper.book().write("cart", cart)
        }

        fun getCartSize(): Int {
            var cartSize = 0
            Cart.getCart().forEach {
                cartSize += it.qty;
            }

            return cartSize
        }
    }
}