package com.example.riotapi.View.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.riotapi.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    
    private val nextIntent by lazy {Intent(this, NickNameActivity::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen()
        }

        showSplashWait()

    }

    private fun showSplashWait() {
        Handler(Looper.getMainLooper()).postDelayed({
            moveToNickNameActivity()
        }, SPLASH_DURATION_TIME)
    }

    private fun moveToNickNameActivity(){
        startActivity(nextIntent)
        finish()
    }

    @Composable
    fun SplashScreen() {

        val splashImageResId = R.drawable.lol_logo

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // 이미지 로딩
            Image(
                painter = painterResource(id = splashImageResId),
                contentDescription = null, // contentDescription를 설정하세요.
                modifier = Modifier
                    .fillMaxSize()
                    .scale(0.7f), // 이미지 크기 조절을 위해 scale을 사용합니다.
                contentScale = ContentScale.Fit
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun SplashScreenPreview() {
        SplashScreen()
    }

    companion object{
        private const val SPLASH_DURATION_TIME : Long = 1500 //1.5초
    }

}