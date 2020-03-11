package com.eosr14.kakaoimagesearch.common.analytics

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class JPAnalyticsProvider(private val context: Context) : ProviderType {
    override fun sendLog(event: String, parameter: HashMap<String, Any>) {
        val eventAndParams = Data.Builder().apply {
            this.putString(KEY_EVENT, event)

            parameter.entries.forEach {
                this.putString(it.key, it.value.toString())
            }
        }

        OneTimeWorkRequestBuilder<JPAnalyticsWorker>()
            .setInputData(eventAndParams.build())
            .build().apply {
                WorkManager.getInstance(context).enqueue(this)
            }
    }

    override fun sendUserProperty(key: String, value: String) {
        // TODO : Send User Property
    }
}