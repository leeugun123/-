package com.example.riotapi.Data.RetrofitData.MatchData

import com.google.gson.annotations.SerializedName

data class Participant(

    @SerializedName("assists")
    val assists : Int,
    @SerializedName("champLevel")
    val champLevel : Int,
    @SerializedName("championId")
    val championId : Int,
    @SerializedName("championName")
    val championName : String,
    @SerializedName("deaths")
    val deaths : Int,
    @SerializedName("kills")
    val kills : Int,
    @SerializedName("profileIcon")
    val profileIcon : Int,
    @SerializedName("puuid")
    val puuid : String,
    @SerializedName("riotIdGameName")
    val riotIdGameName : String,
    @SerializedName("teamPosition")
    val teamPosition : String,
    @SerializedName("win")
    val win : Boolean


)