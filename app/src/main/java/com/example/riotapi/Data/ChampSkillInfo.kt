package com.example.riotapi.Data

import com.google.gson.annotations.SerializedName

data class ChampSkillInfo(

    @SerializedName("puuid")
    val puuid: String,

    @SerializedName("championId")
    val championId: Int,

    @SerializedName("championLevel")
    val championLevel: Int,

    @SerializedName("championPoints")
    val championPoints: Int,

    @SerializedName("lastPlayTime")
    val lastPlayTime: Long,

    @SerializedName("championPointsSinceLastLevel")
    val championPointsSinceLastLevel: Int,

    @SerializedName("championPointsUntilNextLevel")
    val championPointsUntilNextLevel: Int,

    @SerializedName("chestGranted")
    val chestGranted: Boolean,

    @SerializedName("tokensEarned")
    val tokensEarned: Int,

    @SerializedName("summonerId")
    val summonerId: String

)
