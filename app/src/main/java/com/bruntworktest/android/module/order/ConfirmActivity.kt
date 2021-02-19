package com.bruntworktest.android.module.order

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bruntworktest.android.R
import com.bruntworktest.android.module.main.MainActivity
import kotlinx.android.synthetic.main.activity_confirm.*

/**
 * Created by John Vincent Fernandez on 02/19/2021.
 */
class ConfirmActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)
        toolbar.title = getString(R.string.string_title_shop)

        val orderID: Int = intent.getIntExtra("id",0)
        tv_order_id.text = "Your order ID is #${orderID}"

        btn_products.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY))
            finish()
        }
    }

}