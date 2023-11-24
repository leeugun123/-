package com.example.riotapi.Retrofit

import com.example.riotapi.Data.UserInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface RiotApiService {

    @GET("lol/summoner/v4/summoners/by-name/{summoner_name}?api_key=RGAPI-be79bae7-6ddd-41ef-9e8e-97f789649118")
    fun getUserData(@Path(value = "summoner_name", encoded = true) summonerName : String) : retrofit2.Call<UserInfo>


}