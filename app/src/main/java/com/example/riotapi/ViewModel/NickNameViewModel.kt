package com.example.riotapi.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.riotapi.Data.RetrofitData.ChampSkillInfo
import com.example.riotapi.Data.RetrofitData.UserDto
import com.example.riotapi.Data.UserInfo
import com.example.riotapi.Repository.NickNameRepository
import com.example.riotapi.Retrofit.RetrofitApi_Instance
import com.example.riotapi.Retrofit.RetrofitApi_Instance.kr_RetrofitApi
import com.example.riotapi.Retrofit.RiotApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NickNameViewModel(application : Application) : AndroidViewModel(application) {

    private val _summonerInfo = MutableLiveData<UserDto>()
    val summonerInfo : LiveData<UserDto> get() = _summonerInfo

    private var nickNameRepository : NickNameRepository

    init {
        nickNameRepository = NickNameRepository(application)
    }

    fun fetchUserInfo(userNickName : String){

        viewModelScope.launch(Dispatchers.IO) {

            val userInfoResponse = nickNameRepository.fetchUserInfo(userNickName)

            withContext(Dispatchers.Main){
                _summonerInfo.value = userInfoResponse
            }

        }

    }




}