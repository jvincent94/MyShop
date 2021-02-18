package com.bruntworktest.android.module.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bruntworktest.android.R
import com.bruntworktest.android.model.Product
import com.bruntworktest.android.model.Products
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_product.*
import kotlinx.android.synthetic.main.item_product.view.*

/**
 * Created by John Vincent Fernandez on 02/12/2021.
 */
class ProductListAdapter(private val products: MutableList<Product>,
                         private val onClick: (Product) -> Unit)
    : RecyclerView.Adapter<ProductListAdapter.ViewHolder>(){

    fun setProducts(productList: List<Product>){
        products.addAll(productList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false).let {
                ViewHolder(it, onClick)
            }
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(products[position])
    }

    class ViewHolder(override val containerView: View,
                     private val onClick: (Product) -> Unit) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        @SuppressLint("SetTextI18n")
        fun bindData(products: Product) {
            var img: Int = 0
            with(products) {
                tv_category.text = products.category
                tv_name.text = products.name

                when(products.id){
                    "p_1" -> img = R.drawable.p_1
                    "p_2" -> img = R.drawable.p_2
                    "p_3" -> img = R.drawable.p_3
                    "p_4" -> img = R.drawable.p_4
                    "p_5" -> img = R.drawable.p_5
                }
                Picasso.with(containerView.context)
                        .load(img)
                        .error(img)
                        .into(img_product)
                img_product.setBackgroundColor(Color.parseColor(products.bgColor))
                tv_price.text = "$"+ products.price

                itemView.btn_add.setOnClickListener { onClick(this) }

            }
        }
    }
}