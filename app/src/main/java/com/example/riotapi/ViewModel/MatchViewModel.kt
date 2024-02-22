package com.example.riotapi.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.riotapi.Data.RetrofitData.MatchData.GameInfo
import com.example.riotapi.Data.RetrofitData.MatchData.MatchDto
import com.example.riotapi.Data.RetrofitData.MatchData.Participant
import com.example.riotapi.Data.UserInfo
import com.example.riotapi.Repository.MatchRepository
import com.example.riotapi.Retrofit.RetrofitApi_Instance.asia_RetrofitApi
import com.example.riotapi.Retrofit.RiotApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MatchViewModel(application : Application) : AndroidViewModel(application) {

    private val _summonerMatchInfoList = MutableLiveData<List<MatchDto>>()
    val summonerMatchInfoList : LiveData<List<MatchDto>> get() = _summonerMatchInfoList

    private val _summonerMatchInfoDetail = MutableLiveData<MatchDto>()
    val summonerMatchInfoDetail : LiveData<MatchDto> get() = _summonerMatchInfoDetail

    private var matchRepository : MatchRepository

    init{
        matchRepository = MatchRepository(application)
    }

    fun fetchMatchIds(){

        viewModelScope.launch(Dispatchers.IO) {

            val matchIdsResponse = matchRepository.fetchMatchIds()

            withContext(Dispatchers.Main){
                _summonerMatchInfoList.value = matchIdsResponse
            }

        }

    }

    fun fetchMatchInfo(matchId : String){

        viewModelScope.launch(Dispatchers.IO) {

            val matchInfoDetailResponse = matchRepository.fetchMatchInfo(matchId)

            withContext(Dispatchers.Main){
                _summonerMatchInfoDetail.value = matchInfoDetailResponse
            }

        }



    }






}