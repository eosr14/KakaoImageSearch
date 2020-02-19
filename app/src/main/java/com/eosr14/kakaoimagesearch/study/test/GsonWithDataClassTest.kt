package com.eosr14.kakaoimagesearch.study.test

import com.google.gson.annotations.SerializedName

data class GsonWithDataClassTest1(
    @SerializedName("collection")
    val collection: String = "",

    @SerializedName("datetime")
    val dateTime: String = ""
) {
    init {
        println("GsonWithDataClassTest2 Test 111")
    }
}

data class GsonWithDataClassTest2(
    @SerializedName("collection")
    val collection: String,

    @SerializedName("datetime")
    val dateTime: String
) {
    init {
        println("GsonWithDataClassTest2 Test 222")
    }
}