package com.eosr14.kakaoimagesearch.ui.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.eosr14.kakaoimagesearch.R
import com.eosr14.kakaoimagesearch.common.base.BaseActivity
import com.eosr14.kakaoimagesearch.databinding.ActivityDetailBinding
import com.eosr14.kakaoimagesearch.model.KakaoImage

class DetailActivity : BaseActivity(), DetailViewModelInterface {
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityDetailBinding>(
            this@DetailActivity,
            R.layout.activity_detail
        ).apply {
            val document = intent.getParcelableExtra(EXTRA_KAKAO_DOCUMENT) as KakaoImage.Documents
            detailViewModel = DetailViewModel(this@DetailActivity, document)
            viewModel = detailViewModel
            lifecycleOwner = this@DetailActivity
        }
    }

    // [-- DetailViewModelInterface
    override fun openWebBrowser(docUrl: String) =
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(docUrl)))
    // --] DetailViewModelInterface

    companion object {
        private const val EXTRA_KAKAO_DOCUMENT = "kakaoDocument"

        fun startActivity(context: Context, document: KakaoImage.Documents) {
            context.startActivity(Intent(context, DetailActivity::class.java).apply {
                putExtra(EXTRA_KAKAO_DOCUMENT, document)
            })
        }
    }
}