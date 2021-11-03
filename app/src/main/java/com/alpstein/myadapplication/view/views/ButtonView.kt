package com.alpstein.myadapplication.view.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.alpstein.myadapplication.R
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

class ButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1,

    ) : ConstraintLayout(context, attrs, defStyleAttr) {
    private var interstitialButton: Button? = null
    private var showNativeBtn: Button? = null
    private var loadRewardBtn: Button? = null
    private var showRewardBtn: Button? = null
    private var nativeTemplateViewMedium: TemplateView? = null
    private var adView: AdView? = null

    fun initView() {
        interstitialButton = findViewById(R.id.interstitialButton)
        showNativeBtn = findViewById(R.id.showNativeBtn)
        loadRewardBtn = findViewById(R.id.loadRewardBtn)
        showRewardBtn = findViewById(R.id.showRewardBtn)
        nativeTemplateViewMedium = findViewById(R.id.nativeTemplateViewMedium)
        adView = findViewById(R.id.adView)

        interstitialButton!!.text = context.getString(R.string.interstitalBtn)
        showNativeBtn!!.text = "show Native"
        loadRewardBtn!!.text = context.getString(R.string.loadRewardBun)
        showRewardBtn!!.text = "show Reward"
        nativeTemplateViewMedium!!.visibility = View.GONE
        adView!!.adSize = AdSize.BANNER
        adView!!.adUnitId = "ca-app-pub-3940256099942544/6300978111"

    }

}