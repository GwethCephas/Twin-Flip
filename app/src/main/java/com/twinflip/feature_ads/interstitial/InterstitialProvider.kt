package com.twinflip.feature_ads.interstitial

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.twinflip.core.data.utils.AdConstants.INTERSTITIAL_AD_UNIT_ID

object InterstitialProvider {
    private var mInterstitialAds: InterstitialAd? = null
    private var isAdLoading: Boolean = false

    private const val AD_UNIT_ID = INTERSTITIAL_AD_UNIT_ID

    fun loadAd(context: Context) {
        if (mInterstitialAds != null || isAdLoading) return
        isAdLoading = true

        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            context, AD_UNIT_ID, adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mInterstitialAds = null
                    isAdLoading = false
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAds = interstitialAd
                    isAdLoading = false
                }
            }
        )

    }

    fun showAd(activity: Activity, onAdDismissed: () -> Unit) {
        if (mInterstitialAds != null) {
            mInterstitialAds?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    mInterstitialAds = null
                    loadAd(activity)
                    onAdDismissed()
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    mInterstitialAds = null
                    onAdDismissed()
                }
            }
            mInterstitialAds?.show(activity)
        } else {
            onAdDismissed()
        }
    }
}