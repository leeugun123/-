package com.example.riotapi.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.riotapi.databinding.ActivityNickNameBinding

class NickNameActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityNickNameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNickNameBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.inputBut.setOnClickListener {

            if(mBinding.nicknameEditText.text.isNotBlank()){
                //api 통신 호출 및 Room 데이터 저장
            }else
                Toast.makeText(this,"닉네임이 입력되지 않았습니다.",Toast.LENGTH_SHORT).show()

        }


    }


}