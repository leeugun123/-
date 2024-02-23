package com.example.riotapi.Repository

import android.app.Application
import com.example.riotapi.Data.RetrofitData.ChampSkillInfo
import com.example.riotapi.Model.UserInfo
import com.example.riotapi.Retrofit.RetrofitApi_Instance

class ChampSkillRepository() {

    private var champSkillInfoList : List<ChampSkillInfo> = emptyList()

    suspend fun fetchSkillInfo() : List<ChampSkillInfo> {

        val response = RetrofitApi_Instance.kr_RetrofitApi.getChampionSkill(UserInfo.puuId)

        if (response.isSuccessful)
            champSkillInfoList = response.body() ?: emptyList()

        return champSkillInfoList

    }


}