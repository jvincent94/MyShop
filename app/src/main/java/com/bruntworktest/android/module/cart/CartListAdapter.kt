package com.bruntworktest.android.module.cart

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bruntworktest.android.R
import com.bruntworktest.android.model.CartItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_cart.*
import kotlinx.android.synthetic.main.item_cart.view.*
import kotlinx.android.synthetic.main.item_product.tv_name
import kotlinx.android.synthetic.main.item_product.tv_price

/**
 * Created by John Vincent Fernandez on 02/12/2021.
 */
class CartListAdapter(private var items: MutableList<CartItem>,
                      private val onClick: (CartItem) -> Unit)
    : RecyclerView.Adapter<CartListAdapter.ViewHolder>(){

    fun setItems(itemList: List<CartItem>){
        items.clear()
        items.addAll(itemList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false).let {
                ViewHolder(it, onClick)
            }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(items[position], position)
    }

    class ViewHolder(override val containerView: View,
                     private val onClick: (CartItem) -> Unit) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        @SuppressLint("SetTextI18n")
        fun bindData(cartItem: CartItem, pos: Int) {
            tv_name.text = cartItem.product.name
            tv_price.text = "$${cartItem.product.price}"
            tv_qty.text = "x${cartItem.qty}"
            itemView.btn_delete.setOnClickListener { onClick(cartItem) }
        }
    }
}