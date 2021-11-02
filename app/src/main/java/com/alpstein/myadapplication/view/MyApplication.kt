package com.alpstein.myadapplication.view

import android.app.Application
import com.alpstein.myadapplication.handler.AppOpenManager
import com.google.android.gms.ads.MobileAds


/** Application class that initializes, loads and show ads when activities change states. */
class MyApplication : Application() {
    private var appOpenManager: AppOpenManager? = null

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(
            this
        ) { }
        appOpenManager = AppOpenManager(this)
    }
}