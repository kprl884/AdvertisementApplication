package com.alpstein.myadapplication.repository

import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd

class FetchDataImpl : FetchData {
    private val LOG_TAG = "FetchDataImpl"
    private var loadCallback: AppOpenAd.AppOpenAdLoadCallback? = null
    private var appOpenAd: AppOpenAd? = null
    private val AD_UNIT_ID = "ca-app-pub-3940256099942544/3419835294"
    override fun fetchOpenAppAd(context: Context, adRequest: AdRequest): AppOpenAd? {

        loadCallback = object : AppOpenAd.AppOpenAdLoadCallback() {
            /**
             * Called when an app open ad has loaded.
             *
             * @param ad the loaded app open ad.
             */

            override fun onAdLoaded(p0: AppOpenAd) {
                super.onAdLoaded(p0)
                appOpenAd = p0
                Log.d(LOG_TAG, "onAdLoaded in loading")
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
                Log.d(LOG_TAG, "error in loading")
            }
        }

        AppOpenAd.load(
            context, AD_UNIT_ID, adRequest,
            AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback
        )

        return appOpenAd
    }
}