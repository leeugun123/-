package com.example.riotapi.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.riotapi.Data.RetrofitData.MatchData.MatchDto
import com.example.riotapi.Repository.MatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MatchViewModel() : ViewModel() {

    private val _summonerMatchDtoList = MutableLiveData<List<MatchDto>>()
    val summonerMatchDtoList : LiveData<List<MatchDto>> get() = _summonerMatchDtoList

    private var matchRepository = MatchRepository()

    fun fetchMatchIds(){

        viewModelScope.launch(Dispatchers.IO) {

            val matchIdsResponse = matchRepository.fetchMatchIds()

            withContext(Dispatchers.Main){
                _summonerMatchDtoList.value = matchIdsResponse
            }

        }

    }








}