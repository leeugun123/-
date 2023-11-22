package com.example.riotapi.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.riotapi.Data.UserInfo
import com.example.riotapi.LocalDB.DataDao
import com.example.riotapi.Retrofit.RiotApiService
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.security.auth.callback.Callback

class Repository() {

    private val _userInfoData = MutableLiveData<UserInfo>()
    val userInfoData : LiveData<UserInfo> get() = _userInfoData

    private var retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://kr.api.riotgames.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var riotApiService : RiotApiService = retrofit.create(RiotApiService::class.java)


    //private val dataDao: DataDao

    fun fetchUserInfo(){

        riotApiService.getUserData("탈서스").enqueue(object : retrofit2.Callback<UserInfo> {

            override fun onResponse(call: Call<UserInfo>, response : Response<UserInfo>) {
                if (response.isSuccessful) {
                    val dataList = response.body()

                    dataList?.let {

                        _userInfoData.value = it

                        Log.e("TAG",it.puuId)

                        //saveDataToRoom(it)
                    }
                }
                else
                    Log.e("API Call", "Error: ${response.code()}")



            }

            override fun onFailure(call: Call<UserInfo>, t: Throwable) {
                Log.e("API Call", "Failed: ${t.message}")
            }


        })

    }


}