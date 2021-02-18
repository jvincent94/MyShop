package com.bruntworktest.android.module.main

import android.content.Context
import android.util.Log
import com.bruntworktest.android.base.BaseMvpPresenterImpl
import com.bruntworktest.android.model.Cart
import com.bruntworktest.android.model.CartItem
import com.bruntworktest.android.model.Product
import com.bruntworktest.android.model.Products
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.paperdb.Paper
import org.json.JSONArray
import java.io.IOException

/**
 * Created by John Vincent Fernandez on 02/16/2021.
 */
class ProductsPresenter : BaseMvpPresenterImpl<ProductContract.View>(), ProductContract.Presenter {
    override fun init() {
        Paper.init(mView!!.getContext())
        mView!!.showCartCount(Cart.getCartSize())
    }

    override fun loadProducts() {
        val jsonFileString = getJsonData(mView!!.getContext(), "products.json")
        val gson = Gson()
        var products: Products = gson.fromJson(jsonFileString, Products::class.java)
        val productList: List<Product> = products.products

        mView?.showProducts(productList.toMutableList())
    }

    override fun addProduct(cartItem: CartItem) {
        Cart.addItem(cartItem, mView!!.getContext())
        mView!!.showMessage("Item added to cart")
        mView!!.showCartCount(Cart.getCartSize())
    }

    override fun deleteProduct(cartItem: CartItem) {

    }


    private fun getJsonData(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (io: IOException) {
            io.printStackTrace()
            return null
        }

        return jsonString
    }
}