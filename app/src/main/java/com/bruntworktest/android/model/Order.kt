package com.bruntworktest.android.model

/**
 * Created by John Vincent Fernandez on 02/12/2021.
 */
data class Order (
        val orderId: Int,
        val name: String,
        val email: String,
        val total: Double,
        val items: List<CartItem>
) {
}