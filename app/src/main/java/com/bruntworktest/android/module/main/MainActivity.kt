package com.bruntworktest.android.module.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bruntworktest.android.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}