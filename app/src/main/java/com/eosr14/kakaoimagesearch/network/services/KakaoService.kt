package com.eosr14.kakaoimagesearch.network.services


import com.eosr14.kakaoimagesearch.common.KAKAO_REST_API_KEY
import com.eosr14.kakaoimagesearch.model.KakaoImage
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface KakaoService {

    @Headers("Authorization: KakaoAK $KAKAO_REST_API_KEY")
    @GET("/v2/search/image")
    fun requestKakaoSearch(@Query("query") query: String) : Single<KakaoImage>

//    fun requestKakaoSearch(
//        @Query("query") query: String,
//        @Query("sort") sort: String,
//        @Query("page") page: Int,
//        @Query("size") size: Int
//    )


}