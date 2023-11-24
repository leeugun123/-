package com.example.riotapi.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.riotapi.ViewModel.NickNameViewModel
import com.example.riotapi.databinding.ActivityNickNameBinding

class NickNameActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityNickNameBinding
    private lateinit var nickNameViewModel : NickNameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNickNameBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        nickNameViewModel = ViewModelProvider(this)[NickNameViewModel::class.java]


        mBinding.inputBut.setOnClickListener {

            if(mBinding.nicknameEditText.text.isNotBlank()){
                //api 통신 호출 및 Room 데이터 저장
                nickNameViewModel.fetchUserInfo(mBinding.nicknameEditText.text.toString())
            }else
                Toast.makeText(this,"닉네임이 입력되지 않았습니다.",Toast.LENGTH_SHORT).show()

        }




        nickNameViewModel.userInfoData.observe(this, Observer { userData ->

            Log.e("TAG", userData.name + " " + userData.id)

        })


    }


}