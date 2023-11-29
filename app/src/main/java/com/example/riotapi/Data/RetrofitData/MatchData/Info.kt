package com.example.riotapi.Data.RetrofitData.MatchData

import com.google.gson.annotations.SerializedName

data class Info(

    @SerializedName("participants")
    val participants : List<Participant>
)
