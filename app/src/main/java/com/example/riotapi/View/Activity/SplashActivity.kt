package com.example.riotapi.View.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.riotapi.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivitySplashBinding
    private lateinit var nextIntent : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        nextIntent = Intent(this, NickNameActivity::class.java)

        moveNextActivity()

    }


    private fun moveNextActivity(){

        Handler().postDelayed({
            startActivity(nextIntent)
            finish()
        }, SPLASH_TIME_OUT)

    }

    companion object {
        private const val SPLASH_TIME_OUT: Long = 1500 // 1.5초
    }

}