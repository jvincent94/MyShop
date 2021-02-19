package com.bruntworktest.android.module.checkout

import android.os.Environment
import android.util.Log
import com.bruntworktest.android.base.BaseMvpPresenterImpl
import com.bruntworktest.android.model.Cart
import com.bruntworktest.android.model.Order
import com.google.gson.Gson
import io.paperdb.Paper
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.*

class CheckoutPresenter: BaseMvpPresenterImpl<CheckoutContract.View>(), CheckoutContract.Presenter {

    private lateinit var file: File
    override fun init() {
        Paper.init(mView!!.getContext())
        mView!!.showCartCount(Cart.getCartSize())
        mView!!.showSubtotal(Cart.getSubtotal())
    }

    override fun checkout(name: String, email: String) {
        val random = Random()
        var num: Int = random.nextInt(999999)
        var jsonString = Gson().toJson( Order(num, name, email, Cart.getSubtotal(), Cart.getCart()) )

        if(createFile(num)){
            file.writeText(jsonString)
            mView!!.showMessage("Order success")
            mView!!.orderSuccess(num)
        } else {
            checkout(name, email)
        }
    }

    private fun createFile(num: Int): Boolean {
        val fileName = "order_$num.json"
        file = File(mView!!.getContext().filesDir.absolutePath, fileName)
        return file.createNewFile()
    }
}