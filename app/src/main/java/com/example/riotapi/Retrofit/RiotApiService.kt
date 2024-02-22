package com.example.riotapi.Retrofit

import com.example.riotapi.BuildConfig
import com.example.riotapi.Data.RetrofitData.ChampSkillInfo
import com.example.riotapi.Data.RetrofitData.MatchData.MatchDto
import com.example.riotapi.Data.RetrofitData.UserDto
import com.example.riotapi.RIOT_API_KEY
import com.example.riotapi.RIOT_API_KEY.riot_api_key
import com.google.gson.internal.GsonBuildConfig
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface RiotApiService {

    @Headers("X-Riot-Token: $riot_api_key")
    @GET("lol/summoner/v4/summoners/by-name/{summoner_name}")
    suspend fun getUserData(@Path(value = "summoner_name", encoded = true) summonerName : String) : Response<UserDto>


    @Headers("X-Riot-Token: $riot_api_key")
    @GET("lol/champion-mastery/v4/champion-masteries/by-puuid/{encryptedPUUID}")
    suspend fun getChampionSkill(@Path(value = "encryptedPUUID", encoded = true) puuId: String) : Response<List<ChampSkillInfo>>

    @Headers("X-Riot-Token: $riot_api_key")
    @GET("lol/match/v5/matches/by-puuid/{puuid}/ids?count=10")
    suspend fun getMatchIds(@Path(value = "puuid", encoded = true) puuId : String) : Response<List<String>>


    @Headers("X-Riot-Token: $riot_api_key")
    @GET("lol/match/v5/matches/{matchId}")
    suspend fun getMatchInfo(@Path(value = "matchId" , encoded = true) matchId : String) : Response<MatchDto>


}