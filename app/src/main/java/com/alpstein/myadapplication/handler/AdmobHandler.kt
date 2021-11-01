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


const val GAME_LENGTH_MILLISECONDS = 3000L
const val AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712"

class AdmobHandler()  {

    val adRequest: AdRequest = AdRequest.Builder().build()

    private var ctx: Context? = null
    private var mInterstitialAd: InterstitialAd? = null

    private var mAdIsLoading: Boolean = false

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

    // Show the ad if it's ready
    fun showInterstitial(interstitialAd: InterstitialAd?, context: Context, activity: Activity) {
        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d("InterstitialAd", "Ad was dismissed.")
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mInterstitialAd = null
                    interstitialAd(context)
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                    Log.d("InterstitialAd", "Ad failed to show.")
                    // Don't forget to set the ad reference to null so you
                    // don't show the ad a second time.
                    mInterstitialAd = null
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d("InterstitialAd", "Ad showed fullscreen content.")
                    // Called when ad is dismissed.
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
            // Show the ad.
            templateView.setNativeAd(ad)
        }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    // Handle the failure by logging, altering the UI, and so on.
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    val styles =
                        NativeTemplateStyle.Builder().withMainBackgroundColor(ColorDrawable(Color.RED)).build()
                    templateView.setStyles(styles)
                }
            })
            .withNativeAdOptions(
                NativeAdOptions.Builder()
                    // Methods in the NativeAdOptions.Builder class can be
                    // used here to specify individual options settings.
                    .build()
            )
            .build().loadAd(adRequest)
    }
}