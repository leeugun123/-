package com.example.riotapi.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.riotapi.Data.RetrofitData.MatchDto
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

    private var retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://asia.api.riotgames.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var riotApiService : RiotApiService = retrofit.create(RiotApiService::class.java)


    fun fetchMatchIds(puuId : String){

        riotApiService.getMatchIds(puuId).enqueue(object : retrofit2.Callback<List<String>>{

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {

                if(response.isSuccessful){

                    val matchIdLists = response.body()

                    matchIdLists?.let { matchIdList ->

                        // Coroutine scope to launch a new coroutine
                        CoroutineScope(Dispatchers.Main).launch {

                            // List of Deferred jobs for fetchMatchInfo calls
                            val jobs = matchIdList.map { matchId ->
                                async(Dispatchers.IO) {
                                    fetchMatchInfo(matchId)
                                }
                            }

                            // Await for all jobs to complete
                            val matchInfos = jobs.awaitAll()

                            // Process the synchronized matchInfos list
                            processMatchInfos(matchInfos)
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


    private suspend fun fetchMatchInfo(matchId : String) : MatchDto{

        Log.e("TAG","실행")

        return withContext(Dispatchers.IO) {
            val response = riotApiService.getMatchInfo(matchId).execute()
            if (response.isSuccessful) {
                response.body() ?: throw NullPointerException("MatchDto is null")
            } else {
                Log.e("YourActivity", "Error Body: ${response.errorBody()?.string()}")
                throw IOException("Failed to fetch MatchInfo")
            }
        }

    }

    private fun processMatchInfos(matchInfos: List<MatchDto>) {

        Log.e("TAG","종료")


        // Process the synchronized matchInfos list here
        for (matchInfo in matchInfos) {
            // Your processing logic
        }
    }



}