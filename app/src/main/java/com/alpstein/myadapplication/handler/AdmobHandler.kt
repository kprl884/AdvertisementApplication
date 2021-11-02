package com.alpstein.myadapplication.handler

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.widget.Toast
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.ads.nativetemplates.TemplateView
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback

class AdmobHandler {

    val adRequest: AdRequest = AdRequest.Builder().build()
    private var rewardedInterstitialAd: RewardedInterstitialAd? = null
    private var ctx: Context? = null
    private var mInterstitialAd: InterstitialAd? = null
    private val LOG_TAG = "AppOpenManager"

    //      BANNER
    fun bannerListener(view: AdView) {
        view.adListener = object : AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        }
    }

    //      InterstitialAd
    fun interstitialAd(context: Context) {
        InterstitialAd.load(
            context,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                    Toast.makeText(
                        ctx, "Ad Loaded",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    mInterstitialAd = null
                    Toast.makeText(
                        ctx, "Ad Failed to Load",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }

    fun showLoadedAd(): InterstitialAd? {
        return mInterstitialAd
    }


    fun showInterstitial(interstitialAd: InterstitialAd?, context: Context, activity: Activity) {
        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d("InterstitialAd", "Ad was dismissed.")
                    mInterstitialAd = null
                    interstitialAd(context)
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                    Log.d("InterstitialAd", "Ad failed to show.")
                    mInterstitialAd = null
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d("InterstitialAd", "Ad showed fullscreen content.")

                }
            }
            mInterstitialAd?.show(activity)
        } else {
            Toast.makeText(context, "Ad wasn't loaded.", Toast.LENGTH_SHORT).show()
        }
    }

    fun nativeAd(templateView: TemplateView, context: Context) {
        val adLoader = AdLoader.Builder(context, "ca-app-pub-3940256099942544/2247696110")

        adLoader.forNativeAd { ad: NativeAd ->
            templateView.setNativeAd(ad)
        }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    val styles =
                        NativeTemplateStyle.Builder()
                            .withMainBackgroundColor(ColorDrawable(Color.RED)).build()
                    templateView.setStyles(styles)
                }
            })
            .withNativeAdOptions(
                NativeAdOptions.Builder()
                    .build()
            )
            .build().loadAd(adRequest)
    }

    fun loadRewardAd(context: Context) {
        RewardedInterstitialAd.load(context, "ca-app-pub-3940256099942544/5354046379",
            adRequest, object : RewardedInterstitialAdLoadCallback() {
                override fun onAdLoaded(p0: RewardedInterstitialAd) {
                    super.onAdLoaded(p0)
                    rewardedInterstitialAd = p0
                    Log.d(LOG_TAG, "onAdLoaded")
                }

                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    Log.d(LOG_TAG, "onAdFailedToLoad")
                }
            })
    }

    fun showLoadedRewardAd(): RewardedInterstitialAd? {
        return rewardedInterstitialAd
    }

}