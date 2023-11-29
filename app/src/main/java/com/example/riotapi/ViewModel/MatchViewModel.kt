package com.example.riotapi.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.riotapi.Data.RetrofitData.MatchData.GameInfo
import com.example.riotapi.Data.RetrofitData.MatchData.MatchDto
import com.example.riotapi.Data.RetrofitData.MatchData.Participant
import com.example.riotapi.Data.UserInfo
import com.example.riotapi.Retrofit.RetrofitApi_Instance.asia_RetrofitApi
import com.example.riotapi.Retrofit.RiotApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MatchViewModel : ViewModel() {

    private val _summonerMatchInfoList = MutableLiveData<List<MatchDto>>()
    val summonerMatchInfoList : LiveData<List<MatchDto>> get() = _summonerMatchInfoList

    fun fetchMatchIds(){

        asia_RetrofitApi.getMatchIds(UserInfo.puuId).enqueue(object : retrofit2.Callback<List<String>>{

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {

                if(response.isSuccessful){

                    val matchIdLists = response.body()

                    matchIdLists?.let { matchIdList ->

                        CoroutineScope(Dispatchers.Main).launch {

                            val jobs = matchIdList.map { matchId ->
                                async(Dispatchers.IO) {
                                    fetchMatchInfo(matchId)
                                }
                            }
                            _summonerMatchInfoList.value = jobs.awaitAll()
                        }
                    }
                }
                else
                    Log.e("YourActivity", "Error Body: ${response.errorBody()?.string()}")

            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.e("API Call", "Failed: ${t.message}")
            }


        })

    }


    private suspend fun fetchMatchInfo(matchId : String) = withContext(Dispatchers.IO) {

        val response = asia_RetrofitApi.getMatchInfo(matchId).execute()
        if (response.isSuccessful) {
            response.body() ?: throw NullPointerException("MatchDto is null")
        } else {
            Log.e("YourActivity", "Error Body: ${response.errorBody()?.string()}")
            throw IOException("Failed to fetch MatchInfo")
        }

    }



}