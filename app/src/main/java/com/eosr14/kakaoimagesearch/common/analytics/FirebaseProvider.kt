package com.eosr14.kakaoimagesearch.common.analytics

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class FirebaseProvider(private val context: Context) : ProviderType {
    override fun sendLog(event: String, parameter: Bundle) =
        FirebaseAnalytics.getInstance(context).logEvent(event, parameter)
}