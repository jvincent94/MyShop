package com.bruntworktest.android

import android.app.Application
import com.bruntworktest.android.util.TypeFaceUtil

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        val typeFaceUtil = TypeFaceUtil()
        typeFaceUtil.overrideFonts(this, "MONTSERRAT", "fonts/montserrat-regular.ttf")
    }
}