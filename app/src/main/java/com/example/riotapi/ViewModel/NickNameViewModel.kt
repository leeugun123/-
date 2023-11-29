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
import com.example.riotapi.Retrofit.RiotApiService
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NickNameViewModel() : ViewModel() {

    private val _summonerInfo = MutableLiveData<UserDto>()
    val summonerInfo : LiveData<UserDto> get() = _summonerInfo

    fun fetchUserInfo(userNickName : String){

        kr_RetrofitApi.getUserData(userNickName).enqueue(object : retrofit2.Callback<UserDto> {

            override fun onResponse(call: Call<UserDto>, response : Response<UserDto>) {

                if (response.isSuccessful) {
                    val dataList = response.body()
                    dataList?.let {_summonerInfo.value = it}
                }
                else{ Log.e("API Call", "Error: ${response.code()}") }

            }

            override fun onFailure(call: Call<UserDto>, t: Throwable) {
                Log.e("API Call", "Failed: ${t.message}")
            }

        })

    }




}