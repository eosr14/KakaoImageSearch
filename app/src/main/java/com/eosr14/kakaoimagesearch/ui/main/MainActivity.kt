package com.eosr14.kakaoimagesearch.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.eosr14.kakaoimagesearch.R
import com.eosr14.kakaoimagesearch.common.base.BaseActivity
import com.eosr14.kakaoimagesearch.common.base.BaseRecyclerViewAdapter
import com.eosr14.kakaoimagesearch.databinding.ActivityMainBinding
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
                .subscribe { text ->
                    mainViewModel.requestSearchImage(text)
                }
        )

        recyclerview_main.run {
            addItemDecoration(VerticalMarginDecoration(this@MainActivity))
            layoutManager = LinearLayoutManager(context).apply { orientation = RecyclerView.VERTICAL }
            adapter = MainListAdapter(object : BaseRecyclerViewAdapter.OnItemClickListener {
                override fun onItemClick(view: View, position: Int, adapter: BaseRecyclerViewAdapter<*, *>) {
                    Toast.makeText(this@MainActivity, "아이템 클릭 테스트 Position = $position", Toast.LENGTH_SHORT).show()
//                    mainViewModel.onClickReservation((adapter as ReservationListAdapter).getItem(position))
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

    private fun onTextChangeListener(listener:(CharSequence)->Unit) : TextWatcher {
        return object: TextWatcher {
            override fun afterTextChanged(text: Editable?) { }
            override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                text?.let { listener.invoke(it) }
            }
        }
    }

    // MainViewModelInterface [--
    override fun updateRecyclerView() {
//        android.util.Log.d("eosr14", "updateRecyclerView 111")
//        recyclerview_main.adapter?.notifyDataSetChanged()
    }
//    override fun updateRecyclerView() {
//        recyclerview_main.run {
//            layoutManager = LinearLayoutManager(context).apply { orientation = RecyclerView.VERTICAL }
//            adapter = MainListAdapter(object : BaseRecyclerViewAdapter.OnItemClickListener {
//                override fun onItemClick(view: View, position: Int, adapter: BaseRecyclerViewAdapter<*, *>) {
//                    Toast.makeText(this@MainActivity, "아이템 클릭 테스트 Position = $position", Toast.LENGTH_SHORT).show()
////                    mainViewModel.onClickReservation((adapter as ReservationListAdapter).getItem(position))
//                }
//            })
//        }
//    }
    // --] MainViewModelInterface

}

