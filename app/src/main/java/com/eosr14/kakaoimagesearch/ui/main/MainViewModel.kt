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

    private val _isSearchEmpty = MutableLiveData(false)
    val isSearchEmpty: LiveData<Boolean> get() = _isSearchEmpty

    private val _searchText = MutableLiveData("")
    val searchText: LiveData<String> get() = _searchText

    fun requestSearchImage(text: CharSequence) {
        progress.value = true
        _searchText.value = text.toString()
        mainViewModelInterface.scrollTop()

        addDisposable(
            RetrofitManager.requestImageSearch(text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ kakaoImage ->
                    _searchList.value = kakaoImage.documents
                    _isSearchEmpty.value = kakaoImage.documents.isEmpty()
                    progress.value = false
                }, {
                    mainViewModelInterface.showErrorToast()
                    _isSearchEmpty.value = true
                    progress.value = false
                })
        )
    }

}