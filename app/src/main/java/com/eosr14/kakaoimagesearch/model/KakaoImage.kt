package com.eosr14.kakaoimagesearch.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class KakaoImage(
    val documents: List<Documents>,
    val meta: Meta
) {

    @Parcelize
    data class Documents(
        @SerializedName("collection")
        val collection: String = "",

        @SerializedName("dataTime")
        val datetime: String = "",

        @SerializedName("display_sitename")
        val displaySiteName: String = "",

        @SerializedName("doc_url")
        val docUrl: String = "",

        @SerializedName("width")
        val width: Int = 0,

        @SerializedName("height")
        val height: Int = 0,

        @SerializedName("image_url")
        val imageUrl: String = "",

        @SerializedName("thumbnail_url")
        val thumbnailUrl: String = ""
    ) : Parcelable

    data class Meta(
        @SerializedName("total_count")
        val totalCount: Int = 0,

        @SerializedName("pageable_count")
        val pageableCount: Int = 0,

        @SerializedName("is_end")
        val isEnd: Boolean = false
    )
}