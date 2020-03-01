package com.eosr14.kakaoimagesearch.common.analytics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

sealed class JPEvent : EventType {

    class Search(val term: String) : JPEvent()
    class MyProfile(val id: String, val userName: String) : JPEvent()

    override fun eventName(provider: ProviderType): String {
        return when (this) {
            is Search -> FirebaseAnalytics.Event.SEARCH
            is MyProfile -> "MyProfile"
        }
    }

    override fun parameters(provider: ProviderType): Bundle {
        return when (this) {
            is Search -> {
                Bundle().apply {
                    putString(FirebaseAnalytics.Param.SEARCH_TERM, term)
                }
            }
            is MyProfile -> {
                Bundle().apply {
                    putString("id", id)
                    putString("userName", userName)
                }
            }
        }
    }

}