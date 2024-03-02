package com.example.riotapi.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.riotapi.Data.JsonData.Champ.ChampHashMap
import com.example.riotapi.Data.JsonData.Champ.ChampionMap
import com.example.riotapi.Data.JsonData.Spell.SpellHashMap
import com.example.riotapi.Data.JsonData.Spell.SpellMap
import com.example.riotapi.Data.RetrofitData.UserDto
import com.example.riotapi.Model.UserInfo
import com.example.riotapi.View.Fragment.ChampExFragment
import com.example.riotapi.View.Fragment.FightRecordFragment
import com.example.riotapi.R
import com.example.riotapi.ViewModel.NickNameViewModel
import com.example.riotapi.databinding.ActivityNickNameBinding
import com.google.gson.Gson

class NickNameActivity : AppCompatActivity() {

    private lateinit var fa : ChampExFragment
    private lateinit var fb : FightRecordFragment

    private val mBinding by lazy { ActivityNickNameBinding.inflate(layoutInflater) }
    private val nickNameViewModel : NickNameViewModel by viewModels()
    private val fragmentManager by lazy { supportFragmentManager }

    private var selected = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)

        activityInit()
        bindingInit()

        nickNameViewModel.summonerInfo.observe(this) {userDto ->

            syncUserInfo(userDto)

            if (fa.isAdded || fb.isAdded) {

                fragmentManager.beginTransaction().remove(fa).commit()
                fragmentManager.beginTransaction().remove(fb).commit()

                createFragment()
            }

            fragmentCommit()
            fragmentShowUpdate()

        }


    }

    private fun bindingInit() {

        mBinding.apply {

            inputBut.setOnClickListener {
                checkNickNameValid()
            }

            navView.setOnNavigationItemSelectedListener { menuItem ->

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

        }


    }

    private fun checkNickNameValid() {

        val nickName = mBinding.nicknameEditText.text.toString()

        if (nickName.isNotBlank())
            fetchUserInfo(nickName)
        else
            showToast(NOT_INPUT_NICKNAME_MESSAGE)

    }


    private fun showToast(message : String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun fetchUserInfo(nickName : String) {
        nickNameViewModel.fetchUserInfo(nickName)
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
        createFragment()
    }

    private fun jsonParsing() {
        championParsing()
        spellParsing()
    }

    private fun spellParsing() {
        val jsonString = assets.open(SPELL_DATA_SET_JSON_FORMAT).reader().readText()
        val spellMap = Gson().fromJson(jsonString, SpellMap::class.java)

        spellMap.data.map { (spellId, spellData) ->
            SpellHashMap.spellHashInfo[spellData.key] = spellId
        }
    }

    private fun championParsing() {

        val jsonString = assets.open(CHAMP_DATA_SET_JSON_FORMAT).reader().readText()
        val championMap = Gson().fromJson(jsonString, ChampionMap::class.java)

        championMap.data.map { (championId, championData) ->
            ChampHashMap.champHashInfo[championData.key] = championId
        }

    }

    private fun createFragment(){
        fa = ChampExFragment()
        fb = FightRecordFragment()
    }//메모리 누수 문제..


    companion object{
        private const val SPELL_DATA_SET_JSON_FORMAT = "SpellDataSet.json"
        private const val CHAMP_DATA_SET_JSON_FORMAT = "champDataSet.json"

        private const val NOT_INPUT_NICKNAME_MESSAGE = "닉네임이 입력되지 않았습니다."
    }


}
