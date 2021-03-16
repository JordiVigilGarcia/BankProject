package com.android.bankproject.splash_activity.ui

import android.os.Bundle
import com.android.bankproject.commons.BaseActivity
import com.android.bankproject.commons.Constants
import com.android.bankproject.databinding.ActivitySplashBinding
import com.android.bankproject.home_screen.ui.HomeActivity
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

       object : Thread() {
            override fun run() {
               try {
                    sleep(Constants.LOADING_SLEEP)
               } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                   startActivity<HomeActivity>()
                   finish()
                }
           }
        }.start()


    }
}