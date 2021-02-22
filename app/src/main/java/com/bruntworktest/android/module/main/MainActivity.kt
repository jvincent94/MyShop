package com.bruntworktest.android.module.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bruntworktest.android.R
import com.bruntworktest.android.base.BaseMvpActivity
import com.bruntworktest.android.model.CartItem
import com.bruntworktest.android.model.Product
import com.bruntworktest.android.module.cart.CartActivity
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

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
            startActivity(Intent(this, CartActivity::class.java))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
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

    override fun showChips(products: MutableList<Product>) {

        val chip: Chip? = findViewById(chip_group.checkedChipId)
        chip_group.setOnCheckedChangeListener { group, checkedId ->
            (findViewById<Chip>(checkedId))?.let {
                Toast.makeText(this, "${it.text}",Toast.LENGTH_SHORT).show()
            }
        }
        for (item in products.indices){
            chip_group.addChip(this, products[item].category)
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

    override fun onResume() {
        super.onResume()
        mPresenter.init()
    }

    fun ChipGroup.addChip(context: Context, label: String){
        Chip(context).apply {
            id = View.generateViewId()
            text = label
            isClickable = true
            isCheckable = true
            isCheckedIconVisible = false
            isFocusable = true
            addView(this)
        }
    }
}