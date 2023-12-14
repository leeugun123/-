package com.example.riotapi.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.riotapi.Data.RetrofitData.ChampSkillInfo
import com.example.riotapi.Data.RetrofitData.UserDto
import com.example.riotapi.Data.UserInfo
import com.example.riotapi.Retrofit.RetrofitApi_Instance
import com.example.riotapi.Retrofit.RetrofitApi_Instance.kr_RetrofitApi
import com.example.riotapi.Retrofit.RetrofitBuilder
import com.example.riotapi.Retrofit.RiotApiService
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChampSkillViewModel : ViewModel() {

    private val _champSkillInfoList = MutableLiveData<List<ChampSkillInfo>>()
    val champSkillInfoList : LiveData<List<ChampSkillInfo>> get() = _champSkillInfoList

    fun fetchSkillInfo(){

        kr_RetrofitApi.getChampionSkill(UserInfo.puuId).enqueue(object : retrofit2.Callback<List<ChampSkillInfo>> {

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