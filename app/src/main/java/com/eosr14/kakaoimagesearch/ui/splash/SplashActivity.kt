package com.eosr14.kakaoimagesearch.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eosr14.kakaoimagesearch.R
import com.eosr14.kakaoimagesearch.common.SPLASH_INTERVAL
import com.eosr14.kakaoimagesearch.common.base.BaseActivity
import com.eosr14.kakaoimagesearch.ui.main.MainActivity
import com.google.android.gms.ads.AdRequest
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setAdView()
        showMain()
    }

    private fun setAdView() {
        adView?.loadAd(AdRequest.Builder().build())
    }

    private fun showMain() {
        addDisposable(
            Observable
                .interval(SPLASH_INTERVAL, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    MainActivity.startActivity(this)
                    finish()
                }
        )
    }

}