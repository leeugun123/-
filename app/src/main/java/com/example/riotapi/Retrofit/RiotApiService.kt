package com.example.riotapi.Retrofit

import com.example.riotapi.Data.UserInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface RiotApiService {

    @GET("lol/summoner/v4/summoners/by-name/{summoner_name}?api_key=RGAPI-c656c2bf-93b1-4592-857e-884c0ab90ffc")
    fun getUserData(@Path(value = "summoner_name", encoded = true) summonerName : String) : retrofit2.Call<UserInfo>

}