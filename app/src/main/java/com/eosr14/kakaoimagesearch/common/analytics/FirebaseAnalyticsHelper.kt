package com.eosr14.kakaoimagesearch.common.analytics

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

object FirebaseAnalyticsHelper {
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    private var currentScreen: String = ""
    private var prevScreen: String = ""
    private var source: String = ""

    fun init(context: Context) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context)
    }

    fun setSource(source: String) {
        this.source = source
    }

    fun getCurrentScreen(): String {
        return currentScreen
    }

    fun setCurrentScreen(activity: Activity, screenName: String) {
        prevScreen = currentScreen
        currentScreen = screenName
        mFirebaseAnalytics.setCurrentScreen(activity, screenName, null)
    }

    private fun defaultBundle(bundle: Bundle, category: String, action: String, label: String, value: String) {
        bundle.putString(Param.CATEGORY.key, category)
        bundle.putString(Param.ACTION.key, action)
        bundle.putString(Param.LABEL.key, label)
        bundle.putString(Param.VALUE.key, value)
    }

    fun setEventSelectContent(id: String, name: String, type: String) {
        setEventSelectContent(id, name, type, "")
    }

    fun setEventSelectContent(id: String, name: String, type: String, companyId: String) {
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, type)
        bundle.putString(Param.COMPANY_ID.key, companyId)
        setEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }

    fun setJPAdsDisplayEvent(type: String, title: String, id: String, companyId: String, companyName: String) {
        val bundle = Bundle()
        bundle.putString(Param.TYPE.key, type)
        bundle.putString(Param.ID.key, id)
        bundle.putString(Param.TITLE.key, title)
        bundle.putString(Param.COMPANY_ID.key, companyId)
        bundle.putString(Param.COMPANY_NAME.key, companyName)
        setEvent(Event.ADS_DISPLAY.key, bundle)
    }

    fun setJPAdsClickEvent(type: String, title: String, id: String, companyId: String, companyName: String) {
        val bundle = Bundle()
        bundle.putString(Param.TYPE.key, type)
        bundle.putString(Param.ID.key, id)
        bundle.putString(Param.TITLE.key, title)
        bundle.putString(Param.COMPANY_ID.key, companyId)
        bundle.putString(Param.COMPANY_NAME.key, companyName)
        setEvent(Event.ADS_CLICK.key, bundle)
    }

    fun setJPStoryDisplayEvent(storyTitle: String, storyId: String, companyId: String, companyName: String? = "") {
        val bundle = Bundle()
        bundle.putString(Param.ID.key, storyId)
        bundle.putString(Param.TITLE.key, storyTitle)
        bundle.putString(Param.COMPANY_ID.key, companyId)
        bundle.putString(Param.COMPANY_NAME.key, companyName?.let { it } ?: "")
        setEvent(Event.STORY_DISPLAY.key, bundle)
    }

    fun setJPStoryClickEvent(storyTitle: String, storyId: String, companyId: String, companyName: String? = "") {
        val bundle = Bundle()
        bundle.putString(Param.ID.key, storyId)
        bundle.putString(Param.TITLE.key, storyTitle)
        bundle.putString(Param.COMPANY_ID.key, companyId)
        bundle.putString(Param.COMPANY_NAME.key, companyName?.let { it } ?: "")
        setEvent(Event.STORY_CLICK.key, bundle)
    }

    fun setMemberShipActionEvent(category: String, action: String, label: String, value: String = "") {
        val bundle = Bundle()
        defaultBundle(bundle, category, action, label, value)
        bundle.putString(Param.SOURCE.key, source)
        setEvent(Event.MEMBERSHIP_ACTION.key, bundle)
    }

    fun setDisplayEvent(category: String, action: String, label: String, value: String = "") {
        val bundle = Bundle()
        defaultBundle(bundle, category, action, label, value)
        setEvent(Event.DISPLAY_ACTION.key, bundle)
    }

    fun setImpressionEvent(category: String, action: String, label: String, value: String = "") {
        val bundle = Bundle()
        defaultBundle(bundle, category, action, label, value)
        setEvent(Event.IMPRESS_ACTION.key, bundle)
    }

    fun setWriteEvent(category: String, action: String, label: String, value: String = "") {
        val bundle = Bundle()
        defaultBundle(bundle, category, action, label, value)
        setEvent(Event.WRITE.key, bundle)
    }

    fun setFilterEvent(category: String, action: String, label: String, value: String = "") {
        val bundle = Bundle()
        defaultBundle(bundle, category, action, label, value)
        setEvent(Event.FILTER.key, bundle)
    }

    fun setCompanyFollowEvent(category: String, action: String, label: String, value: String = "") {
        val bundle = Bundle()
        defaultBundle(bundle, category, action, label, value)
        setEvent(Event.COMPANY_FOLLOW.key, bundle)
    }

    fun setSearchEvent(term: String) {
        setEvent(
                FirebaseAnalytics.Event.SEARCH,
                Bundle().apply {
                    putString(FirebaseAnalytics.Param.SEARCH_TERM, term)
                }
        )
    }

    fun setViewItemEvent(itemId: String, companyId: String) {
        setEvent(FirebaseAnalytics.Event.VIEW_ITEM,
                Bundle().apply {
                    putString(Param.ITEM_ID.key, itemId)
                    putString(Param.COMPANY_ID.key, companyId)
                })
    }

    fun setFireBaseEvent(event: Event, bundle: Bundle) {
        setEvent(event.key, bundle)
    }

    private fun setEvent(eventName: String, bundle: Bundle) {
        bundle.putString("prev_screen", prevScreen)
        bundle.putString("screen", currentScreen)
        mFirebaseAnalytics.logEvent(eventName, bundle)
    }

    fun setUserProperty(key: String, value: String) {
        mFirebaseAnalytics.setUserProperty(key, value)
    }

    enum class UserProperty(val key: String) {
        STATE("state"),
        MEMBERSHIP("membership"),
        USER_ID("jp_id")
    }

    enum class Event(val key: String) {
        IMPRESS_ACTION("impression"),
        DISPLAY_ACTION("display"),
        MEMBERSHIP_ACTION("membership_action"),
        FILTER("filter"),
        WRITE("write"),
        COMPANY_FOLLOW("company_follow"),
        ADS_DISPLAY("jp_ads_display"),
        ADS_CLICK("jp_ads_click"),
        STORY_DISPLAY("jp_story_display"),
        STORY_CLICK("jp_story_click")
    }

    enum class Param(val key: String) {
        LABEL("label"),
        CATEGORY("category"),
        ACTION("action"),
        VALUE("value"),
        SOURCE("source"),
        TYPE("type"),
        ID("id"),
        TITLE("title"),
        COMPANY_NAME("company_name"),
        COMPANY_ID("company_id"),
        ITEM_ID("item_id"),
        TERM("term"),
        SECTION("section")
    }

    enum class AdsType(val key: String) {
        BIG_BANNER("big_banner")
    }
}