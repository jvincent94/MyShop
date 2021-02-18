package com.bruntworktest.android.module.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bruntworktest.android.R
import com.bruntworktest.android.base.BaseMvpActivity
import com.bruntworktest.android.model.CartItem
import com.bruntworktest.android.model.Product
import com.bruntworktest.android.model.Products
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

/**
 * Created by John Vincent Fernandez on 02/12/2021.
 */

class MainActivity : BaseMvpActivity<ProductContract.View, ProductsPresenter>(), ProductContract.View  {

    private var mAdapter: ProductListAdapter? = null
    override var mPresenter: ProductsPresenter = ProductsPresenter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        mPresenter.init()
        mPresenter.loadProducts()

        toolbar.title = getString(R.string.string_title_shop)

        btn_cart.setOnClickListener {

        }
    }

    override fun showProducts(products: MutableList<Product>) {
        mAdapter?.setProducts(products)
        mAdapter?.notifyDataSetChanged()
    }

    override fun showCartCount(count: Int) {
        if(count > 0){
            tv_cart_quantity.visibility = View.VISIBLE
            tv_cart_quantity.text = count.toString()
        } else {
            tv_cart_quantity.visibility = View.GONE
        }
    }

    private fun setUpRecyclerView() {
        mAdapter = ProductListAdapter(ArrayList<Product>()) {
            val item = CartItem(it)
            mPresenter.addProduct(item)
        }
        recyclerview_products.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerview_products.adapter = mAdapter
    }

    override fun showError(error: String?) {
        super.showError(error)
        progress_bar.visibility = View.GONE
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}