package com.eosr14.kakaoimagesearch.model

import com.google.gson.annotations.SerializedName

data class KakaoImage(
    val documents: List<Documents>,
    val meta: Meta
) {

    data class Documents(
        @SerializedName("collection")
        val collection: String,

        @SerializedName("dataTime")
        val datetime: String,

        @SerializedName("display_sitename")
        val displaySiteName: String,

        @SerializedName("doc_url")
        val docUrl: String,

        @SerializedName("width")
        val width: Int,

        @SerializedName("height")
        val height: Int,

        @SerializedName("image_url")
        val imageUrl: String,

        @SerializedName("thumbnail_url")
        val thumbnailUrl: String
    )

    data class Meta(
        @SerializedName("total_count")
        val totalCount: Int,

        @SerializedName("pageable_count")
        val pageableCount: Int,

        @SerializedName("is_end")
        val isEnd: Boolean
    )
}