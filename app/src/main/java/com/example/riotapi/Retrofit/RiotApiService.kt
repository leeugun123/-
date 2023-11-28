package com.example.riotapi.Retrofit

import com.example.riotapi.Data.RetrofitData.ChampSkillInfo
import com.example.riotapi.Data.RetrofitData.MatchDto
import com.example.riotapi.Data.RetrofitData.UserInfo
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RiotApiService {

    @GET("lol/summoner/v4/summoners/by-name/{summoner_name}?api_key=RGAPI-053aeb41-c12c-4439-a7ec-9938b942d7de")
    fun getUserData(@Path(value = "summoner_name", encoded = true) summonerName : String) : retrofit2.Call<UserInfo>

    @GET("lol/champion-mastery/v4/champion-masteries/by-summoner/{summonerId}?api_key=RGAPI-053aeb41-c12c-4439-a7ec-9938b942d7de")
    fun getChampionSkill(@Path(value = "summonerId", encoded = true) summonerId : String) : retrofit2.Call<List<ChampSkillInfo>>

    @Headers(
        "X-Riot-Token: RGAPI-053aeb41-c12c-4439-a7ec-9938b942d7de"
    )
    @GET("lol/match/v5/matches/by-puuid/{puuid}/ids?count=5")
    fun getMatchIds(@Path(value = "puuid", encoded = true) puuId : String) : retrofit2.Call<List<String>>


    @Headers(
        "X-Riot-Token: RGAPI-053aeb41-c12c-4439-a7ec-9938b942d7de"
    )
    @GET("lol/match/v5/matches/{matchId}")
    fun getMatchInfo(@Path(value = "matchId" , encoded = true) matchId : String) : retrofit2.Call<MatchDto>




}