package com.example.riotapi.Data.RetrofitData.MatchData

import com.google.gson.annotations.SerializedName

data class GameInfo(

    @SerializedName("participants")
    val participants : List<Participant>
)
