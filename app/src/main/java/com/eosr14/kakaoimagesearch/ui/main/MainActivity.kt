package com.eosr14.kakaoimagesearch.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eosr14.kakaoimagesearch.R
import com.eosr14.kakaoimagesearch.common.ExitDialog
import com.eosr14.kakaoimagesearch.common.VerticalMarginDecoration
import com.eosr14.kakaoimagesearch.common.analytics.AnalyticsManager
import com.eosr14.kakaoimagesearch.common.base.BaseActivity
import com.eosr14.kakaoimagesearch.common.base.BaseRecyclerViewAdapter
import com.eosr14.kakaoimagesearch.databinding.ActivityMainBinding
import com.eosr14.kakaoimagesearch.ui.detail.DetailActivity
import com.eosr14.kakaoimagesearch.ui.inapp.InAppActivity
import com.google.firebase.analytics.FirebaseAnalytics
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity(), MainViewModelInterface {

    private lateinit var mainViewModel: MainViewModel

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

    override fun onBackPressed() {
        if (!this@MainActivity.isFinishing) {
            ExitDialog(
                this@MainActivity,
                Runnable { finish() },
                Runnable { }
            ).show()
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
                    AnalyticsManager.sendLog(
                        FirebaseAnalytics.Event.SEARCH,
                        hashMapOf(
                            FirebaseAnalytics.Param.SEARCH_TERM to term,
                            "key_test" to "test1234"
                        )
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
    override fun onClickInApp() = InAppActivity.startActivity(this@MainActivity)
    // --] MainViewModelInterface

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

}

