package com.eosr14.kakaoimagesearch.common.analytics

import android.content.Context
import android.os.Bundle
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class JPAnalyticsProvider(private val context: Context) : ProviderType {
    override fun sendLog(event: String, parameter: Bundle) {
        val event = Data.Builder().apply {
            putString(KEY_EVENT, event)
        }
        val parameter = Data.Builder().apply {
            parameter.keySet().forEach { key ->
                putString(key, parameter.getString(key))
            }
        }

        OneTimeWorkRequestBuilder<JPAnalyticsWorker>()
            .setInputData(event.build())
            .setInputData(parameter.build())
            .build().apply {
                WorkManager.getInstance(context).enqueue(this)
            }
    }
}