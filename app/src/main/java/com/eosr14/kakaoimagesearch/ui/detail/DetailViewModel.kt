package com.eosr14.kakaoimagesearch.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eosr14.kakaoimagesearch.common.base.BaseViewModel
import com.eosr14.kakaoimagesearch.model.KakaoImage

class DetailViewModel(
    private val detailViewModelInterface: DetailViewModelInterface,
    private val originalDocument: KakaoImage.Documents
) : BaseViewModel() {

    private val _document = MutableLiveData(originalDocument)
    val document: LiveData<KakaoImage.Documents> get() = _document

    fun onDetailSubItemClick(docUrl: String) = detailViewModelInterface.openWebBrowser(docUrl)

    fun test() {
        println("a")
        println("b")
        println("c")
    }
}