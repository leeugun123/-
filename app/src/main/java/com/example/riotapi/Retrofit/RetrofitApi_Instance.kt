package com.example.riotapi.Retrofit

object RetrofitApi_Instance {

    val kr_RetrofitApi = RetrofitBuilder().retrofitBuilder("https://kr.api.riotgames.com/")
    val asia_RetrofitApi = RetrofitBuilder().retrofitBuilder("https://asia.api.riotgames.com/")

}