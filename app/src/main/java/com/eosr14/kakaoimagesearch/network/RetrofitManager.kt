package com.eosr14.kakaoimagesearch.network

import com.eosr14.kakaoimagesearch.common.KAKAO_BASE_URL
import com.eosr14.kakaoimagesearch.model.KakaoImage
import com.eosr14.kakaoimagesearch.network.services.KakaoService
import io.reactivex.Single


object RetrofitManager {

    private fun provideKakao(): KakaoService {
        return RetrofitClient().provideRetrofit(KAKAO_BASE_URL).create(KakaoService::class.java)
    }

    fun requestImageSearch(query: String, page: Int): Single<KakaoImage> {
        return provideKakao().requestKakaoSearch(query, page)
    }

}