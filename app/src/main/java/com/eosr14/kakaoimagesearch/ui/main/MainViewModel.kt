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

    private val _page = MutableLiveData(1)
    val page: LiveData<Int> get() = _page

    private val _isEnd = MutableLiveData(false)
    val isEnd: LiveData<Boolean> get() = _isEnd

    private val _isScrollBottom = MutableLiveData(false)
    val isScrollBottom: LiveData<Boolean> get() = _isScrollBottom

    fun requestSearchImage(text: CharSequence, isBottom: Boolean) {
        progress.value = true
        _searchList.value = mutableListOf()
        _searchText.value = text.toString()
        _isScrollBottom.value = isBottom

        when (isBottom) {
            true -> _page.value = _page.value?.plus(1)
            false -> _page.value = 1
        }

        _page.value?.let { page ->
            addDisposable(
                RetrofitManager.requestImageSearch(text.toString(), page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ kakaoImage ->
                        _isEnd.value = kakaoImage.meta.isEnd
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

}