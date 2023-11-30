package com.example.riotapi.Data.RetrofitData.MatchData

import com.google.gson.annotations.SerializedName

data class Challenges(

    @SerializedName("kda")
    var kda : Double,

    @SerializedName("killParticipation")
    var killParticipation : Double
)
