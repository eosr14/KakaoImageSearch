package com.eosr14.kakaoimagesearch.network

import com.eosr14.kakaoimagesearch.common.KAKAO_BASE_URL
import com.eosr14.kakaoimagesearch.network.services.KakaoService


object RetrofitManager {

//    private fun provideWebHook(): WebHookService {
//        return RetrofitClient().provideRetrofit(WEB_HOOK_BASE_URL).create(WebHookService::class.java)
//    }
//
//    fun sendBusinessMessage(param: WebHook, callback: ResponseCallBack<WebHook>) {
//        provideWebHook().sendBusinessMessage(MeetingRoomPreference.getWebHookUrl(), param).enqueue(callback)
//    }

//    fun users(query: String, page: Int, perPage: Int) = getApi().users(query, page, perPage)

//    private fun provideGitHub() : Any {
//        return ""
//    }

    private fun privideKakao() : KakaoService {
        return RetrofitClient().provideRetrofit(KAKAO_BASE_URL).create(KakaoService::class.java)
    }

}