package com.bruntworktest.android.module.checkout

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bruntworktest.android.R
import com.bruntworktest.android.base.BaseMvpActivity
import com.bruntworktest.android.module.order.ConfirmActivity
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity : BaseMvpActivity<CheckoutContract.View, CheckoutPresenter>(), CheckoutContract.View {

    override var mPresenter: CheckoutPresenter = CheckoutPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        mPresenter.init()
        toolbar.title = getString(R.string.string_title_shop)

        btn_pay.setOnClickListener {
            mPresenter.checkout(et_name.text.toString(), et_email.text.toString(), switch_terms.isChecked)
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
        startActivity(Intent(this, ConfirmActivity::class.java).putExtra("id", orderId))
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }


}