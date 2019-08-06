package com.eosr14.kakaoimagesearch.network

interface ResponseListener<T> {
    fun onSuccess(model : T)
    fun onError(message: String)
}