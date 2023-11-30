package com.example.riotapi.Data.RetrofitData.MatchData

import com.google.gson.annotations.SerializedName

data class Participant(

    @SerializedName("assists")
    val assists : Int,

    @SerializedName("challenges")
    val challenges : Challenges,

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
    val win : Boolean,

    @SerializedName("item0")
    val item0 : Int,

    @SerializedName("item1")
    val item1 : Int,

    @SerializedName("item2")
    val item2 : Int,

    @SerializedName("item3")
    val item3 : Int,

    @SerializedName("item4")
    val item4 : Int,

    @SerializedName("item5")
    val item5 : Int,

    @SerializedName("item6")
    val item6 : Int,

    @SerializedName("summoner1Id")
    val spell_1 : Int,

    @SerializedName("summoner2Id")
    val spell_2 : Int



)