package com.example.riotapi.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.riotapi.Adapter.ChampSkillAdapter
import com.example.riotapi.Data.JsonData.ChampHashMap
import com.example.riotapi.Data.JsonData.ChampionMap
import com.example.riotapi.ViewModel.NickNameViewModel
import com.example.riotapi.databinding.ActivityNickNameBinding
import com.google.gson.Gson

class NickNameActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityNickNameBinding
    private lateinit var nickNameViewModel : NickNameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNickNameBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initSetting()

        mBinding.inputBut.setOnClickListener {

            if(mBinding.nicknameEditText.text.isNotBlank()){
                //api 통신 호출 및 Room 데이터 저장
                nickNameViewModel.fetchUserInfo(mBinding.nicknameEditText.text.toString())
            }else
                Toast.makeText(this,"닉네임이 입력되지 않았습니다.",Toast.LENGTH_SHORT).show()

        }

        nickNameViewModel.champSkillInfoList.observe(this) { skillInfo ->

            mBinding.champSkillRecycler.adapter = ChampSkillAdapter(skillInfo)

        }

        jsonParsing()


    }

    private fun jsonParsing() {

        val jsonString = assets.open("champDataSet.json").reader().readText()

        val championMap = Gson().fromJson(jsonString, ChampionMap::class.java)

        championMap.data?.map {(championId, championData) ->
           // Log.e("TAG", championData.key + " " + championData.image.full)
            ChampHashMap.champHashInfo.put(championData.key , championId)
        }


    }

    private fun initSetting() {
        nickNameViewModel = ViewModelProvider(this)[NickNameViewModel::class.java]
        mBinding.champSkillRecycler.layoutManager = LinearLayoutManager(this)
    }


}