package com.example.riotapi.View

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

        nextIntent = if(checkNickName()){
            Intent(this, MainActivity::class.java)
        }else
            Intent(this, NickNameActivity::class.java)


        moveNextActivity()

    }

    private fun checkNickName(): Boolean {
        return true
    }

    private fun moveNextActivity(){

        Handler().postDelayed({
            // 메인 화면으로 이동하는 Intent 설정
            startActivity(nextIntent)

            // 현재 액티비티 종료
            finish()
        }, SPLASH_TIME_OUT)

    }

    companion object {
        private const val SPLASH_TIME_OUT: Long = 1500 // 1.5초
    }

}