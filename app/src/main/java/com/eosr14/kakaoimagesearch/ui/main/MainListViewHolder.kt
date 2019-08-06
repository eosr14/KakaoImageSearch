package com.eosr14.kakaoimagesearch.ui.main

import com.eosr14.kakaoimagesearch.common.base.BaseViewHolder
import com.eosr14.kakaoimagesearch.databinding.ItemSearchListBinding
import com.eosr14.kakaoimagesearch.model.KakaoImage

class MainListViewHolder(private val binding: ItemSearchListBinding) :
    BaseViewHolder<KakaoImage.Documents>(binding.root) {
    override fun bind(item: KakaoImage.Documents) {
        binding.document = item
    }
}