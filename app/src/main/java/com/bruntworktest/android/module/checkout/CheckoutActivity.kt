package com.bruntworktest.android.module.checkout

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bruntworktest.android.R
import com.bruntworktest.android.base.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.activity_checkout.toolbar
import kotlinx.android.synthetic.main.activity_checkout.tv_cart_quantity
import kotlinx.android.synthetic.main.activity_main.*

class CheckoutActivity : BaseMvpActivity<CheckoutContract.View, CheckoutPresenter>(), CheckoutContract.View {

    override var mPresenter: CheckoutPresenter = CheckoutPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        mPresenter.init()
        toolbar.title = getString(R.string.string_title_shop)

        btn_pay.setOnClickListener {
            mPresenter.checkout(et_name.text.toString(), et_email.text.toString())
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showSubtotal(total: Double) {
        btn_pay.text = "Pay $${total}"
    }

    override fun showCartCount(count: Int) {
        if(count > 0){
            tv_cart_quantity.visibility = View.VISIBLE
            tv_cart_quantity.text = count.toString()
        } else {
            tv_cart_quantity.visibility = View.GONE
        }
    }

    override fun orderSuccess(orderId: Int) {

    }


}