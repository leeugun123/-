package com.example.riotapi.Repository

import android.app.Application
import com.example.riotapi.Data.RetrofitData.UserDto
import com.example.riotapi.Retrofit.RetrofitApi_Instance

class NickNameRepository() {

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