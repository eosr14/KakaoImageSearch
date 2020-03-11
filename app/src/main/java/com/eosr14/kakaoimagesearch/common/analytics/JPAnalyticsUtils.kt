package com.eosr14.kakaoimagesearch.common.analytics

const val KEY_EVENT = "key_event"

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

interface ProviderType {
    fun sendLog(event: String, parameter: HashMap<String, Any>)
    fun sendUserProperty(key: String, value: String)
}

interface AnalyticsType {
    fun register(provider: ProviderType)
    fun sendLog(eventName: String, parameter: HashMap<String, Any>)
}
