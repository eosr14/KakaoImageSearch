package com.eosr14.kakaoimagesearch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eosr14.kakaoimagesearch.common.base.BaseViewModel
import com.eosr14.kakaoimagesearch.model.KakaoImage
import com.eosr14.kakaoimagesearch.network.RetrofitManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val mainViewModelInterface: MainViewModelInterface
) : BaseViewModel() {

    private val _searchList = MutableLiveData<List<KakaoImage.Documents>>(mutableListOf())
    val searchList: LiveData<List<KakaoImage.Documents>> get() = _searchList

    fun requestSearchImage(text: CharSequence) {
        progress.value = true

        addDisposable(
            RetrofitManager.requestImageSearch(text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ kakaoImage ->
                    android.util.Log.d("eosr14", "kakaolist size = ${kakaoImage.documents.size}")
//                    searchList.value = kakaoImage.documents
                    _searchList.value = kakaoImage.documents
                    progress.value = false
                    mainViewModelInterface.updateRecyclerView()

                }, { exception ->
                    android.util.Log.d("eosr14", "Error message = ${exception.message}")
                    progress.value = false
                })
        )
    }

}