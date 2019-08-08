package com.eosr14.kakaoimagesearch.common

import android.graphics.drawable.Animatable
import android.net.Uri
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eosr14.kakaoimagesearch.model.KakaoImage
import com.eosr14.kakaoimagesearch.ui.main.MainListAdapter
import com.eosr14.kakaoimagesearch.ui.main.MainViewModel
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.RotationOptions
import com.facebook.imagepipeline.image.ImageInfo
import com.facebook.imagepipeline.request.ImageRequestBuilder


object DataBindingComponents {

    @JvmStatic
    @BindingAdapter("searchItems", "isScrollBottom")
    fun setSearchItems(
        recyclerView: RecyclerView,
        items: MutableList<KakaoImage.Documents>,
        isScrollBottom: Boolean
    ) {
        recyclerView.adapter?.let { adapter ->
            if (adapter is MainListAdapter) {
                when (isScrollBottom) {
                    true -> adapter.pushItems(items)
                    false -> adapter.setItems(items)
                }
                recyclerView.scheduleLayoutAnimation()
            }
        }
    }

    @JvmStatic
    @BindingAdapter("scrollBottomListener")
    fun setScrollBottomListener(recyclerView: RecyclerView, viewModel: MainViewModel) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val isEnd = viewModel.isEnd.value ?: false
                val page = viewModel.page.value ?: 1

                if (!recyclerView.canScrollVertically(1) && page < MAX_PAGE_COUNT && !isEnd) {
                    android.util.Log.d("eosr14", "requestTest")
                    viewModel.requestSearchImage(viewModel.searchText.value ?: "", true)
                }
            }
        })
    }

    @JvmStatic
    @BindingAdapter("urlToImage")
    fun setUrlToImage(view: SimpleDraweeView, url: String) {
        val imageRequest = ImageRequestBuilder
            .newBuilderWithSource(Uri.parse(url))
            .setRotationOptions(RotationOptions.autoRotate())
            .setProgressiveRenderingEnabled(true)
            .build()

        view.controller = Fresco.newDraweeControllerBuilder().run {
            this.oldController = view.controller
            this.imageRequest = imageRequest
            this.autoPlayAnimations = true
            this.controllerListener = object : BaseControllerListener<ImageInfo>() {
                override fun onFinalImageSet(
                    id: String?,
                    imageInfo: ImageInfo?,
                    animatable: Animatable?
                ) {
                    super.onFinalImageSet(id, imageInfo, animatable)
                    imageInfo?.let { info ->
                        view.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                        view.aspectRatio = info.width.toFloat() / info.height
                    }
                }
            }
            this.build()
        }
    }

    @JvmStatic
    @BindingAdapter("displayDate")
    fun setDisPlayDate(textView: TextView, dateTime: String) {
        textView.text = convertDisplayDate(dateTime)
    }

}