package com.eosr14.kakaoimagesearch.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResponseCallBack<T>(private val responseListener: ResponseListener<T>) : Callback<T> {
    override fun onFailure(call: Call<T>, throwable: Throwable) {
        responseListener.onError(throwable.message ?: "Api Request Error")
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        response.body()?.let {
            responseListener.onSuccess(it)
        } ?: responseListener.onError(response.message())
    }
}