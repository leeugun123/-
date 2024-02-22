package com.example.riotapi.Repository

import android.app.Application
import android.util.Log
import com.example.riotapi.Data.RetrofitData.MatchData.MatchDto
import com.example.riotapi.Data.UserInfo
import com.example.riotapi.Retrofit.RetrofitApi_Instance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class MatchRepository(application : Application) {

    private var matchDtoList : List<MatchDto> = emptyList()
    private lateinit var matchDto : MatchDto

    suspend fun fetchMatchIds() : List<MatchDto> {

       val apiResponse =  RetrofitApi_Instance.asia_RetrofitApi.getMatchIds(UserInfo.puuId)

        if(apiResponse.isSuccessful)
            matchDtoList = (apiResponse.body() ?: emptyList()) as List<MatchDto>

        return matchDtoList

    }

    suspend fun fetchMatchInfo(matchId : String) : MatchDto{

        try {

            val apiResponse = RetrofitApi_Instance.asia_RetrofitApi.getMatchInfo(matchId)

            if (apiResponse.isSuccessful)
                matchDto = apiResponse.body()!!

        } catch (e: Exception) { }

        return matchDto

    }



}