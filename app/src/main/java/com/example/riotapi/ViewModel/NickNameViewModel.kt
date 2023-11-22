package com.example.riotapi.ViewModel

import androidx.lifecycle.ViewModel
import com.example.riotapi.Repository.Repository

class NickNameViewModel() : ViewModel() {

    val userInfoData = Repository().userInfoData

    fun fetchUserInfo(){
        Repository().fetchUserInfo()
    }


}