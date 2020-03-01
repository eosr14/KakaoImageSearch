package com.eosr14.kakaoimagesearch.common.analytics

import android.os.Bundle

interface ProviderType {
    fun sendLog(event: String, parameter: Bundle)
}

interface EventType {
    fun eventName(provider: ProviderType): String
    fun parameters(provider: ProviderType): Bundle
}

interface AnalyticsType<T> {
    fun register(provider: ProviderType)
    fun sendLog(event: T)
}

class AnalyticsManager<T : EventType> : AnalyticsType<T> {

    private val providers: ArrayList<ProviderType> = arrayListOf()

    override fun register(provider: ProviderType) {
        providers.add(provider)
    }

    override fun sendLog(event: T) {
        providers.forEach { provider ->
            provider.sendLog(
                event.eventName(provider),
                event.parameters(provider)
            )
        }
    }

    companion object {
        fun getInstance() = AnalyticsManager<JPEvent>()
    }

}