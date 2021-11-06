package com.alpstein.myadapplication.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.alpstein.myadapplication.R
import com.alpstein.myadapplication.databinding.FragmentHomeBinding
import com.alpstein.myadapplication.handler.AdmobHandler
import com.alpstein.myadapplication.view.views.ButtonView
import com.google.android.gms.ads.interstitial.InterstitialAd


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val admobHandler = AdmobHandler()
    private lateinit var buttonView: ButtonView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        admobHandler.interstitialAd(requireContext())
        return inflater.inflate(R.layout.fragment_home, container, false).apply {
            buttonView = this as ButtonView
        }
        //return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonView.initView()
        binding.apply {

            adView.loadAd(admobHandler.adRequest)
            admobHandler.bannerListener(binding.adView)

            interstitialButton.setOnClickListener {
                interstitialShow()
            }

            showNativeBtn.setOnClickListener {
                //can set small or medium native template
                binding.nativeTemplateViewSmall.visibility = View.VISIBLE
                admobHandler.nativeAd(binding.nativeTemplateViewSmall, requireContext())
            }

            loadRewardBtn.setOnClickListener {
                context?.let { it1 -> admobHandler.loadRewardAd(requireContext()) }
            }

            showRewardBtn.setOnClickListener {
                rewardedInterstitialShow()
            }
        }


    }

    private fun interstitialShow() {
        val mInterstitialAd2: InterstitialAd? = admobHandler.showLoadedAd()
        if (mInterstitialAd2 != null) {
            mInterstitialAd2.show(activity)
        } else {
            Toast.makeText(
                context, "Ad is Null or Not Loaded",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun rewardedInterstitialShow() {
        admobHandler.showLoadedRewardAd()?.show(activity) { rewardItem ->
            Log.d("TAG", rewardItem.type + rewardItem.amount)
        }
    }

}