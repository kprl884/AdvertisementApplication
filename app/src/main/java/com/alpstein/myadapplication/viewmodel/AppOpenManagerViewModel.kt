package com.alpstein.myadapplication.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alpstein.myadapplication.repository.FetchDataImpl
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.appopen.AppOpenAd
import kotlinx.coroutines.launch

class AppOpenManagerViewModel() : ViewModel() {
    private var fetchDataImpl: FetchDataImpl = FetchDataImpl()
    private val _appOpenAd = MutableLiveData<AppOpenAd>()
    val appOpenAd: MutableLiveData<AppOpenAd>
        get() = _appOpenAd


    fun getAppOpedAd(context: Context, adRequest: AdRequest) {
        viewModelScope.launch {
            appOpenAd.value = fetchDataImpl.fetchOpenAppAd(context, adRequest)
        }
    }
}