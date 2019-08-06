package com.eosr14.kakaoimagesearch.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.eosr14.kakaoimagesearch.R
import com.eosr14.kakaoimagesearch.common.base.BaseActivity
import com.eosr14.kakaoimagesearch.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this@MainActivity, R.layout.activity_main).apply {
            mainViewModel = MainViewModel()
            viewModel = mainViewModel
        }
    }
}

