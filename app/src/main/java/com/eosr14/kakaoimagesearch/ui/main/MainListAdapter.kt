package com.eosr14.kakaoimagesearch.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eosr14.kakaoimagesearch.R
import com.eosr14.kakaoimagesearch.common.base.BaseRecyclerViewAdapter
import com.eosr14.kakaoimagesearch.model.KakaoImage

class MainListAdapter(onItemClickListener: OnItemClickListener) : BaseRecyclerViewAdapter<KakaoImage.Documents, MainListViewHolder>() {

    init {
        this.onItemClickListener = onItemClickListener
    }

    override fun onBindView(holder: MainListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MainListViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_search_list, parent, false))
    }
}