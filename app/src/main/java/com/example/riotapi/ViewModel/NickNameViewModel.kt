package com.example.riotapi.ViewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.riotapi.Data.ChampSkillInfo
import com.example.riotapi.Data.UserInfo
import com.example.riotapi.Retrofit.RiotApiService
import com.example.riotapi.View.NickNameActivity
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NickNameViewModel() : ViewModel() {

    private val _userInfoData = MutableLiveData<UserInfo>()
    val userInfoData : LiveData<UserInfo> get() = _userInfoData

    private var retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://kr.api.riotgames.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var riotApiService : RiotApiService = retrofit.create(RiotApiService::class.java)


    fun fetchUserInfo(userNickName : String){

        riotApiService.getUserData(userNickName).enqueue(object : retrofit2.Callback<UserInfo> {

            override fun onResponse(call: Call<UserInfo>, response : Response<UserInfo>) {

                if (response.isSuccessful) {
                    val dataList = response.body()

                    dataList?.let {
                        _userInfoData.value = it
                    }
                }
                else{
                    Log.e("API Call", "Error: ${response.code()}")
                }



            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                Log.e("API Call", "Failed: ${t.message}")
            }

        })

    }

    fun fetchSkillInfo(summonerId : String){

        riotApiService.getChampionSkill(summonerId).enqueue(object : retrofit2.Callback<List<ChampSkillInfo>> {

            override fun onResponse(call: Call<List<ChampSkillInfo>>, response : Response<List<ChampSkillInfo>>) {

                if (response.isSuccessful) {

                    val dataList = response.body()

                    dataList?.let {
                        Log.e("TAG", it[0].championId.toString())
                    }
                }
                else{
                    Log.e("API Call", "Error: ${response.code()}")
                }



            }

            override fun onFailure(call: Call<List<ChampSkillInfo>>, t: Throwable) {
                Log.e("API Call", "Failed: ${t.message}")
            }

        })



    }




}