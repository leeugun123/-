package com.example.riotapi.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
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

        setContent {
            ComposeNickNameScreen()
        }

        jsonParsing()




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

    private fun checkNickNameValid(nickName : String , tagLine : String) {

        if (nickName.isNotBlank() && tagLine.isNotBlank())
            fetchUserInfo(nickName)
        else
            showToast(NOT_INPUT_NICKNAME_AND_TAGLINE_MESSAGE)

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
        createFragment()
    }

    private fun jsonParsing() {
        championParsing()
        spellParsing()
    }


    private fun createFragment(){
        fa = ChampExFragment()
        fb = FightRecordFragment()
    }//메모리 누수 문제..


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ComposeNickNameScreen() {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            var nickName by remember { mutableStateOf("") }
            var tagLine by remember { mutableStateOf("") }
            var isButtonClicked by remember { mutableStateOf(false) }


            OutlinedTextField(
                value = nickName,
                onValueChange = { nickName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                label = { Text("소환사 이름 입력") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Handle done action
                    }
                )
            )
            //소환사 이름 입력 EditText


            OutlinedTextField(
                value = tagLine,
                onValueChange = { tagLine = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                label = { Text("# 태그 라인 입력") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Handle done action
                    }
                )
            )

            Button(
                onClick = {
                    checkNickNameValid(nickName , tagLine)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)

            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "전송"
                )
                
            }




        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ComposeNickNameScreenPreview() {
        ComposeNickNameScreen()
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



    companion object{

        private const val SPELL_DATA_SET_JSON_FORMAT = "SpellDataSet.json"
        private const val CHAMP_DATA_SET_JSON_FORMAT = "champDataSet.json"

        private const val NOT_INPUT_NICKNAME_AND_TAGLINE_MESSAGE = "소환사 이름과 태그 라인 모두 입력 되지 않았습니다."


    }


}
