package com.example.riotapi.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.riotapi.Data.JsonData.ChampHashMap
import com.example.riotapi.Data.JsonData.ChampionMap
import com.example.riotapi.Data.RetrofitData.UserDto
import com.example.riotapi.Data.UserInfo
import com.example.riotapi.View.Fragment.ChampExFragment
import com.example.riotapi.View.Fragment.FightRecordFragment
import com.example.riotapi.R
import com.example.riotapi.ViewModel.ChampSkillViewModel
import com.example.riotapi.ViewModel.MatchViewModel
import com.example.riotapi.ViewModel.NickNameViewModel
import com.example.riotapi.databinding.ActivityNickNameBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson

class NickNameActivity : AppCompatActivity() , BottomNavigationView.OnNavigationItemSelectedListener{

    private lateinit var mBinding : ActivityNickNameBinding
    private lateinit var nickNameViewModel : NickNameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNickNameBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        activityInit()

        mBinding.inputBut.setOnClickListener {

            val nickName = mBinding.nicknameEditText.text.toString()

            if(nickName.isNotBlank()){
                nickNameViewModel.fetchUserInfo(nickName)
            }else
                Toast.makeText(this,"닉네임이 입력되지 않았습니다.",Toast.LENGTH_SHORT).show()

        }

        nickNameViewModel.summonerInfo.observe(this){
            syncUserInfo(it)
            supportFragmentManager.beginTransaction().add(R.id.fragment_container, ChampExFragment()).commit()
        }


    }

    private fun syncUserInfo(it: UserDto?) {

        val user = it!!

        UserInfo.name = user.name
        UserInfo.id = user.id
        UserInfo.summonerLevel = user.summonerLevel
        UserInfo.puuId = user.puuId
        UserInfo.profileIconId = user.profileIconId
        UserInfo.revisionDate = user.revisionDate
        UserInfo.accountId = user.accountId

    }

    private fun activityInit() {
        jsonParsing()
        nickNameViewModel = ViewModelProvider(this)[NickNameViewModel::class.java]
        mBinding.navView.setOnNavigationItemSelectedListener(this)
    }

    private fun jsonParsing() {

        val jsonString = assets.open("champDataSet.json").reader().readText()

        val championMap = Gson().fromJson(jsonString, ChampionMap::class.java)

        championMap.data?.map {(championId, championData) ->
            ChampHashMap.champHashInfo.put(championData.key , championId)
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.navigation_championExperience -> {

                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, ChampExFragment())
                    .commitAllowingStateLoss()

                return true;
            }
            R.id.navigation_fightRecord -> {

                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, FightRecordFragment()).commitAllowingStateLoss()

                return true;
            }
        }
        return false
    }


}