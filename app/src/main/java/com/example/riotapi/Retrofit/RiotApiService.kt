package com.example.riotapi.Retrofit

import com.example.riotapi.Data.RetrofitData.ChampSkillInfo
import com.example.riotapi.Data.RetrofitData.UserInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface RiotApiService {

    @GET("lol/summoner/v4/summoners/by-name/{summoner_name}?api_key=RGAPI-df1bfab2-4a1a-4f8a-849c-4870365456f8")
    fun getUserData(@Path(value = "summoner_name", encoded = true) summonerName : String) : retrofit2.Call<UserInfo>

    @GET("lol/champion-mastery/v4/champion-masteries/by-summoner/{summonerId}?api_key=RGAPI-df1bfab2-4a1a-4f8a-849c-4870365456f8")
    fun getChampionSkill(@Path(value = "summonerId", encoded = true) summonerId : String) : retrofit2.Call<List<ChampSkillInfo>>



}