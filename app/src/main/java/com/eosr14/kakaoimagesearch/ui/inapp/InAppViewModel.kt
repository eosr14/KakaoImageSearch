package com.eosr14.kakaoimagesearch.ui.inapp


import com.android.billingclient.api.BillingClient
import com.eosr14.kakaoimagesearch.common.base.BaseViewModel

class InAppViewModel(
    private val inAppViewModelInterface: InAppViewModelInterface
) : BaseViewModel() {

    fun onClickTest1() {
        val storeCode = "com.eosr14.kakaoimagesearch.001"
        val skuType = BillingClient.SkuType.INAPP
        inAppViewModelInterface.onClickPurchases(storeCode, skuType)
    }

    fun onClickTest2() {
        val storeCode = "com.eosr14.kakaoimagesearch.002"
        val skuType = BillingClient.SkuType.INAPP
        inAppViewModelInterface.onClickPurchases(storeCode, skuType)
    }

    fun onClickTest3() {
        val storeCode = "com.eosr14.kakaoimagesearch.003"
        val skuType = BillingClient.SkuType.INAPP
        inAppViewModelInterface.onClickPurchases(storeCode, skuType)
    }

    fun onClickTestSubStandard() {
        val storeCode = "com.eosr14.kakaoimagesearch.standard_sub_001"
        val skuType = BillingClient.SkuType.SUBS
        inAppViewModelInterface.onClickPurchases(storeCode, skuType)
    }

    fun onClickTestSubPremium() {
        val storeCode = "com.eosr14.kakaoimagesearch.premium_sub_001"
        val skuType = BillingClient.SkuType.SUBS
        inAppViewModelInterface.onClickPurchases(storeCode, skuType)
    }

}