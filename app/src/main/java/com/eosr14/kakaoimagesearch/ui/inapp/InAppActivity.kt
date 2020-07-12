package com.eosr14.kakaoimagesearch.ui.inapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.billingclient.api.*
import com.eosr14.kakaoimagesearch.R
import com.eosr14.kakaoimagesearch.databinding.ActivityInAppBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class InAppActivity : AppCompatActivity(), InAppViewModelInterface {

    private lateinit var inAppViewModel: InAppViewModel

    private val purchaseUpdateListener =
        PurchasesUpdatedListener { billingResult, purchases -> }

    private lateinit var billingClient: BillingClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityInAppBinding>(
            this@InAppActivity,
            R.layout.activity_in_app
        ).apply {
            inAppViewModel = InAppViewModel(this@InAppActivity)
            lifecycleOwner = this@InAppActivity
            bindView()
        }
    }

    override fun onClickPurchases(storeCode: String, skuType: String) {
        querySkuDetails(storeCode, skuType)
    }

    private fun onClickLaunchBillingFlow(skuDetails: SkuDetails) {
        val flowParams = BillingFlowParams.newBuilder()
            .setSkuDetails(skuDetails)
            .build()

        billingClient.launchBillingFlow(this@InAppActivity, flowParams)
    }

    private fun bindView() {
        initBillingClient()
    }

    private fun initBillingClient() {
        // 결제모듈 3.0 버전 테스트
        billingClient = BillingClient.newBuilder(this@InAppActivity)
            .setListener(purchaseUpdateListener)
            .enablePendingPurchases()
            .build()

        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                }
            }

            override fun onBillingServiceDisconnected() {
                // TODO : Google Play 연결이 끊어졌거나 실패했을 시 작업과정 (재시도 로직을 구현한다면 필요함)
            }
        })
    }

    fun querySkuDetails(storeCode: String, skuType: String) {
        val skuList = ArrayList<String>()
        skuList.add(storeCode)

        val params = SkuDetailsParams.newBuilder()
        params.setSkusList(skuList).setType(skuType)

        GlobalScope.async {
            withContext(Dispatchers.IO) {
                billingClient.querySkuDetails(params.build()).apply {
                    this.skuDetailsList?.let {
                        if (it.isNotEmpty()) {
                            onClickLaunchBillingFlow(it[0])
                        }
                    }
                }
            }
        }
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, InAppActivity::class.java))
        }
    }

}