package com.bruntworktest.android.module.cart

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bruntworktest.android.R
import com.bruntworktest.android.base.BaseMvpActivity
import com.bruntworktest.android.model.Cart
import com.bruntworktest.android.model.CartItem
import com.bruntworktest.android.model.Product
import com.bruntworktest.android.module.checkout.CheckoutActivity
import com.bruntworktest.android.module.main.ProductListAdapter
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.progress_bar
import kotlinx.android.synthetic.main.activity_main.recyclerview_products
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_main.tv_cart_quantity
import java.util.ArrayList
import kotlin.math.roundToInt

/**
 * Created by John Vincent Fernandez on 02/19/2021.
 */

class CartActivity : BaseMvpActivity<CartContract.View, CartPresenter>(), CartContract.View {

    private var mAdapter: CartListAdapter? = null
    override var mPresenter: CartPresenter = CartPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setUpRecyclerView()
        mPresenter.init()
        mPresenter.loadItems()

        toolbar.title = getString(R.string.string_title_shop)

        btn_buy.setOnClickListener {
            startActivity(Intent(this, CheckoutActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = CartListAdapter(ArrayList<CartItem>()) {
            mPresenter.deleteItem(it)
        }
        recyclerview_products.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerview_products.adapter = mAdapter
    }

    override fun showItems(cartItems: MutableList<CartItem>) {
        mAdapter?.setItems(cartItems)
        mAdapter?.notifyDataSetChanged()
    }

    override fun showCartCount(count: Int) {
        if(count > 0){
            tv_cart_quantity.visibility = View.VISIBLE
            layout_checkout.visibility = View.VISIBLE
            tv_cart_quantity.text = count.toString()
        } else {
            tv_cart_quantity.visibility = View.GONE
            layout_checkout.visibility = View.GONE
        }
    }

    @SuppressLint("SetTextI18n")
    override fun showSubTotal(subTotal: Double) {
        tv_subtotal.text = "$"+subTotal.roundToInt()
    }

    override fun showError(error: String?) {
        super.showError(error)
        progress_bar.visibility = View.GONE
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        mPresenter.init()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

}