package com.example.riotapi.Repository

import android.app.Application
import android.util.Log
import com.example.riotapi.Data.RetrofitData.UserDto
import com.example.riotapi.Retrofit.RetrofitApi_Instance
import retrofit2.Call
import retrofit2.Response

class NickNameRepository(application: Application) {

    private lateinit var userDto : UserDto
    suspend fun fetchUserInfo(userNickName : String) : UserDto {

        try {

            val apiResponse = RetrofitApi_Instance.kr_RetrofitApi.getUserData(userNickName)

            if(apiResponse.isSuccessful)
                userDto = apiResponse.body()!!

        }catch (e : Exception){}

        return userDto

    }


}