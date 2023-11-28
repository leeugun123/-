package com.example.riotapi.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.riotapi.Data.RetrofitData.ChampSkillInfo
import com.example.riotapi.Data.RetrofitData.UserInfo
import com.example.riotapi.Retrofit.RiotApiService
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NickNameViewModel() : ViewModel() {


    private val _champSkillInfoList = MutableLiveData<List<ChampSkillInfo>>()
    val champSkillInfoList : LiveData<List<ChampSkillInfo>> get() = _champSkillInfoList

    private val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://kr.api.riotgames.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val riotApiService : RiotApiService = retrofit.create(RiotApiService::class.java)


    fun fetchUserInfo(userNickName : String){

        riotApiService.getUserData(userNickName).enqueue(object : retrofit2.Callback<UserInfo> {

            override fun onResponse(call: Call<UserInfo>, response : Response<UserInfo>) {

                if (response.isSuccessful) {
                    val dataList = response.body()

                    dataList?.let {
                        fetchSkillInfo(it.id)
                        MatchViewModel().fetchMatchIds(it.puuId)
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

    private fun fetchSkillInfo(summonerId : String){

        riotApiService.getChampionSkill(summonerId).enqueue(object : retrofit2.Callback<List<ChampSkillInfo>> {

            override fun onResponse(call: Call<List<ChampSkillInfo>>, response : Response<List<ChampSkillInfo>>) {

                if (response.isSuccessful) {
                    val dataList = response.body()
                    dataList?.let { _champSkillInfoList.value = it }
                }
                else{ Log.e("API Call", "Error: ${response.code()}") }

            }

            override fun onFailure(call: Call<List<ChampSkillInfo>>, t: Throwable) {
                Log.e("API Call", "Failed: ${t.message}")
            }

        })

    }



}