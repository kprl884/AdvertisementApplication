package com.alpstein.myadapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.alpstein.myadapplication.R
import com.alpstein.myadapplication.databinding.FragmentHomeBinding
import com.alpstein.myadapplication.handler.AdmobHandler
import com.google.android.gms.ads.interstitial.InterstitialAd


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val admobHandler = AdmobHandler()
    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        admobHandler.interstitialAd(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //          BANNER
        binding.adView.loadAd(admobHandler.adRequest)
        admobHandler.bannerListener(binding.adView)
        //             INTERSTITIAL
        binding.interstitialButton.setOnClickListener {
            interstitialShow()
        }

        binding.showNativeBtn.setOnClickListener {
            //can set small or medium native template
            admobHandler.nativeAd(binding.nativeTemplateViewSmall, requireContext())
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


}