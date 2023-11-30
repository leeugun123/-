package com.example.riotapi.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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

class NickNameActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityNickNameBinding
    private lateinit var nickNameViewModel: NickNameViewModel
    private lateinit var fa: ChampExFragment
    private lateinit var fb: FightRecordFragment
    private lateinit var fragmentManager: FragmentManager

    private var selected = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityNickNameBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        activityInit()

        mBinding.inputBut.setOnClickListener {
            val nickName = mBinding.nicknameEditText.text.toString()

            if (nickName.isNotBlank()) {
                nickNameViewModel.fetchUserInfo(nickName)
            } else {
                Toast.makeText(this, "닉네임이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        mBinding.navView.setOnNavigationItemSelectedListener { menuItem ->
            selected = when (menuItem.itemId) {
                R.id.navigation_championExperience -> {
                    showFragment(fa)
                    hideFragment(fb)
                    0
                }

                R.id.navigation_fightRecord -> {
                    showFragment(fb)
                    hideFragment(fa)
                    1
                }

                else -> throw IllegalArgumentException("Invalid ItemId")
            }
            true
        }

        nickNameViewModel.summonerInfo.observe(this) {
            syncUserInfo(it)

            // Remove existing fragments
            if (fa.isAdded || fb.isAdded) {
                fragmentManager.beginTransaction().remove(fa).commit()
                fragmentManager.beginTransaction().remove(fb).commit()

                createFragment()
            }

            fragmentCommit()
            fragmentShowUpdate()

        }


    }

    private fun fragmentCommit(){
        fragmentManager.beginTransaction().add(R.id.fragment_container, fa).commit()
        fragmentManager.beginTransaction().add(R.id.fragment_container, fb).commit()
    }

    private fun fragmentShowUpdate() {
        if(selected == 0){
            showFragment(fa)
            hideFragment(fb)
        }else{
            showFragment(fb)
            hideFragment(fa)
        }
    }

    private fun showFragment(fragment: Fragment) {
        fragmentManager.beginTransaction().show(fragment).commit()
    }

    private fun hideFragment(fragment: Fragment) {
        fragmentManager.beginTransaction().hide(fragment).commit()
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
        fragmentManager = supportFragmentManager
        createFragment()
    }

    private fun jsonParsing() {
        val jsonString = assets.open("champDataSet.json").reader().readText()
        val championMap = Gson().fromJson(jsonString, ChampionMap::class.java)

        championMap.data?.map { (championId, championData) ->
            ChampHashMap.champHashInfo[championData.key] = championId
        }
    }

    private fun createFragment(){
        fa = ChampExFragment()
        fb = FightRecordFragment()
    }//메모리 누수 문제..





}
