package com.example.riotapi.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.riotapi.Data.RetrofitData.UserDto
import com.example.riotapi.Repository.NickNameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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