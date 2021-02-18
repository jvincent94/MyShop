package com.bruntworktest.android.model

import com.google.gson.annotations.SerializedName

/**
 * Created by John Vincent Fernandez on 02/12/2021.
 */
data class Products(
    @SerializedName("products") val products: List<Product>
)

data class Product(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("category") val category: String,
    @SerializedName("price") val price: String,
    @SerializedName("bgColor") val bgColor: String
)