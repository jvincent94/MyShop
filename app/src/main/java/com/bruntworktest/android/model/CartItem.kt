package com.bruntworktest.android.model

/**
 * Created by John Vincent Fernandez on 02/16/2021.
 */
data class CartItem(
    var product: Product,
    var qty: Int = 0
) {
}