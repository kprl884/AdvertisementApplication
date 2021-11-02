package com.alpstein.myadapplication.repository

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.appopen.AppOpenAd

interface FetchData {

    fun fetchOpenAppAd(context: Context, adRequest: AdRequest): AppOpenAd?
}