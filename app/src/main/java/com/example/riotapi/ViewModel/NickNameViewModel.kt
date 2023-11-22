package com.example.riotapi.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.riotapi.Data.UserInfo
import com.example.riotapi.LocalDB.AppDatabase
import com.example.riotapi.LocalDB.DataDao
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




    fun fetchUserInfo(applicationContext : Context){

        riotApiService.getUserData("탈서스").enqueue(object : retrofit2.Callback<UserInfo> {

            override fun onResponse(call: Call<UserInfo>, response : Response<UserInfo>) {
                if (response.isSuccessful) {
                    val dataList = response.body()

                    dataList?.let {
                        _userInfoData.value = it

                        val db = Room.databaseBuilder(
                            applicationContext, AppDatabase::class.java, "database"
                        ).allowMainThreadQueries().build()


                        db.dataDao().insertData(it)
                        var s = db.dataDao().getAllData().name

                        Log.e("Tag",s)


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

    private fun saveDataToRoom(userInfo : UserInfo) {


    }


}