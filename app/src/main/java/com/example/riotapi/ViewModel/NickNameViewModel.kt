package com.example.riotapi.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.riotapi.Data.RetrofitData.ChampSkillInfo
import com.example.riotapi.Data.RetrofitData.UserDto
import com.example.riotapi.Data.UserInfo
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

        riotApiService.getUserData(userNickName).enqueue(object : retrofit2.Callback<UserDto> {

            override fun onResponse(call: Call<UserDto>, response : Response<UserDto>) {

                if (response.isSuccessful) {
                    val dataList = response.body()

                    dataList?.let {

                        syncUserInfo(it)
                        //static 변수에 동기화

                        fetchSkillInfo()
                        //챔피언 숙련도 가져오기

                        MatchViewModel().fetchMatchIds()
                        //대전기록 가져오기

                    }

                }
                else{
                    Log.e("API Call", "Error: ${response.code()}")
                }


            }

            private fun syncUserInfo(it: UserDto) {
                UserInfo.name = it.name
                UserInfo.id = it.id
                UserInfo.accountId = it.accountId
                UserInfo.puuId = it.puuId
                UserInfo.profileIconId = it.profileIconId
                UserInfo.revisionDate = it.revisionDate
                UserInfo.summonerLevel = it.summonerLevel
            }


            override fun onFailure(call: Call<UserDto>, t: Throwable) {
                Log.e("API Call", "Failed: ${t.message}")
            }

        })

    }

    private fun fetchSkillInfo(){

        riotApiService.getChampionSkill(UserInfo.id).enqueue(object : retrofit2.Callback<List<ChampSkillInfo>> {

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