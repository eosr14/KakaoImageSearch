package com.eosr14.kakaoimagesearch.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eosr14.kakaoimagesearch.R
import com.eosr14.kakaoimagesearch.common.VerticalMarginDecoration
import com.eosr14.kakaoimagesearch.common.analytics.ApplicationAnalytics
import com.eosr14.kakaoimagesearch.common.base.BaseActivity
import com.eosr14.kakaoimagesearch.common.base.BaseRecyclerViewAdapter
import com.eosr14.kakaoimagesearch.databinding.ActivityMainBinding
import com.eosr14.kakaoimagesearch.ui.detail.DetailActivity
import com.google.firebase.analytics.FirebaseAnalytics
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity(), MainViewModelInterface {

    private lateinit var mainViewModel: MainViewModel
    private val applicationAnalytics = ApplicationAnalytics(this@MainActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(
            this@MainActivity,
            R.layout.activity_main
        ).apply {
            mainViewModel = MainViewModel(this@MainActivity)
            bindView()
            viewModel = mainViewModel
            lifecycleOwner = this@MainActivity
        }
    }

    private fun bindView() {
        val editText = Observable.create<CharSequence> { emitter ->
            edittext_main_search.run {
                addTextChangedListener(onTextChangeListener { text -> emitter.onNext(text) })
            }
        }

        mainViewModel.addDisposable(
            editText.debounce(1000L, TimeUnit.MILLISECONDS)
                .filter { text -> text.isNotEmpty() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { term ->
                    applicationAnalytics.sendEventLog(
                        FirebaseAnalytics.Event.SEARCH,
                        Bundle().apply {
                            putString(FirebaseAnalytics.Param.SEARCH_TERM, term.toString())
                        }
                    )
                    mainViewModel.requestSearchImage(term, false)
                }
        )

        recyclerview_main.run {
            addItemDecoration(VerticalMarginDecoration(this@MainActivity))
            layoutManager =
                LinearLayoutManager(context).apply { orientation = RecyclerView.VERTICAL }
            adapter = MainListAdapter(object : BaseRecyclerViewAdapter.OnItemClickListener {
                override fun onItemClick(
                    view: View,
                    position: Int,
                    adapter: BaseRecyclerViewAdapter<*, *>
                ) {
                    DetailActivity.startActivity(
                        context,
                        (adapter as MainListAdapter).getItem(position)
                    )
                }
            })
        }



        mainViewModel.isProgress.observe(this@MainActivity, Observer {
            it?.let { isProgress ->
                when (isProgress) {
                    true -> progressDialog.show()
                    false -> progressDialog.cancel()
                }
            }
        })
    }

    private fun onTextChangeListener(listener: (CharSequence) -> Unit): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(text: Editable?) {}
            override fun beforeTextChanged(
                text: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                text?.let { listener.invoke(it) }
            }
        }
    }

    // MainViewModelInterface [--
    override fun showErrorToast() = showNetworkErrorToast()
    // --] MainViewModelInterface

}

