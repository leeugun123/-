package com.example.riotapi.View.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.riotapi.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val mBinding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    private val nextIntent by lazy {Intent(this, NickNameActivity::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        waitSplashTime()

    }

    private fun waitSplashTime(){

        Handler(Looper.getMainLooper()).postDelayed({
            moveToNickNameActivity()
        }, SPLASH_TIME_OUT)

    }

    private fun moveToNickNameActivity() {
        startActivity(nextIntent)
        finish()
    }


    companion object {
        private const val SPLASH_TIME_OUT: Long = 1500 // 1.5초
    }

}