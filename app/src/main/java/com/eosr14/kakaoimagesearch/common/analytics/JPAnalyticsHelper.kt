package com.eosr14.kakaoimagesearch.common.analytics

import android.content.Context
import android.os.Bundle
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.google.firebase.analytics.FirebaseAnalytics

class ApplicationAnalytics(private val context: Context) : AnalyticsProvider {
    override fun sendEventLog(event: String, params: Bundle) {
        // FA
        FirebaseAnalytics.getInstance(context).logEvent(event, params)


        // JP Log
        OneTimeWorkRequestBuilder<JPAnalyticsWorker>()
            .build().apply {
            WorkManager.getInstance(context).enqueue(this)
        }
    }

}

interface AnalyticsProvider {
    fun sendEventLog(event: String, params: Bundle)
//    fun fireBaseAnalytics(event: String, params: Bundle)
//    fun jobPlanetAnalytics(event: String, params: Bundle)
}

enum class Event(val key: String) {
    MEMBERSHIP_ACTION("membership_action"),
    SEARCH(FirebaseAnalytics.Event.SEARCH)
}