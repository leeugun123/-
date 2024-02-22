package com.example.riotapi.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.riotapi.Data.RetrofitData.ChampSkillInfo
import com.example.riotapi.Repository.ChampSkillRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChampSkillViewModel(application : Application) : AndroidViewModel(application) {

    private val _champSkillInfoList = MutableLiveData<List<ChampSkillInfo>>()
    val champSkillInfoList : LiveData<List<ChampSkillInfo>> get() = _champSkillInfoList

    private var champSkillRepository : ChampSkillRepository
    init {
        champSkillRepository = ChampSkillRepository(application)
    }

    fun fetchSkillInfo(){

        viewModelScope.launch(Dispatchers.IO){

            val skillInfoResponse = champSkillRepository.fetchSkillInfo()

            withContext(Dispatchers.Main){
                _champSkillInfoList.value = skillInfoResponse
            }

        }

    }


}