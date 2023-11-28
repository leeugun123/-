package com.example.riotapi.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.riotapi.Retrofit.RiotApiService
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

                    matchIdLists?.let {

                        for (matchId in it){
                            Log.e("API Call", matchId)
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



}