package com.bruntworktest.android.util

import android.content.Context
import android.graphics.Typeface
import android.util.Log

class TypeFaceUtil {

    fun overrideFonts(context: Context, defaultFontToOverride:String, customFontFileNameInAssets:String){
        try {
            val customTypeface = Typeface.createFromAsset(context.assets,customFontFileNameInAssets)
            val defaultTypefaceField = Typeface::class.java.getDeclaredField(defaultFontToOverride)
            defaultTypefaceField.isAccessible = true
            defaultTypefaceField.set(null,customTypeface)
        }catch (e:Exception){
            Log.e("CustomFonts","Cannot set font $customFontFileNameInAssets instead of $defaultFontToOverride")
        }
    }
}