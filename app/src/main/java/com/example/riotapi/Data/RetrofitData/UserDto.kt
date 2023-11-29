package com.example.riotapi.Data.RetrofitData

import com.google.gson.annotations.SerializedName

data class UserDto(

    @SerializedName("id")
    val id : String,

    @SerializedName("accountId")
    val accountId : String,

    @SerializedName("puuid")
    val puuId : String,

    @SerializedName("name")
    val name : String,

    @SerializedName("profileIconId")
    val profileIconId : Long,

    @SerializedName("revisionDate")
    val revisionDate : Long,

    @SerializedName("summonerLevel")
    val summonerLevel : Long

)
