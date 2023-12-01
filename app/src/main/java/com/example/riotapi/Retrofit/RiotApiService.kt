package com.example.riotapi.Retrofit

import com.example.riotapi.Data.RetrofitData.ChampSkillInfo
import com.example.riotapi.Data.RetrofitData.MatchData.MatchDto
import com.example.riotapi.Data.RetrofitData.UserDto

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface RiotApiService {

    @GET("lol/summoner/v4/summoners/by-name/{summoner_name}?api_key=RGAPI-7ed27a16-54e8-4b22-9eea-7187b14f2390")
    fun getUserData(@Path(value = "summoner_name", encoded = true) summonerName : String) : retrofit2.Call<UserDto>

    @GET("lol/champion-mastery/v4/champion-masteries/by-summoner/{summonerId}?api_key=RGAPI-7ed27a16-54e8-4b22-9eea-7187b14f2390")
    fun getChampionSkill(@Path(value = "summonerId", encoded = true) summonerId : String) : retrofit2.Call<List<ChampSkillInfo>>

    @Headers(
        "X-Riot-Token: RGAPI-7ed27a16-54e8-4b22-9eea-7187b14f2390"
    )
    @GET("lol/match/v5/matches/by-puuid/{puuid}/ids?count=10")
    fun getMatchIds(@Path(value = "puuid", encoded = true) puuId : String) : retrofit2.Call<List<String>>


    @Headers(
        "X-Riot-Token: RGAPI-7ed27a16-54e8-4b22-9eea-7187b14f2390"
    )
    @GET("lol/match/v5/matches/{matchId}")
    fun getMatchInfo(@Path(value = "matchId" , encoded = true) matchId : String) : retrofit2.Call<MatchDto>




}