package com.example.riotapi.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    fun retrofitBuilder(uri : String) = Retrofit.Builder()
                                                .baseUrl(uri)
                                                .addConverterFactory(GsonConverterFactory.create())
                                                .build().create(RiotApiService::class.java)


}