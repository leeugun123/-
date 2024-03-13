package com.example.riotapi.View.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.example.riotapi.MainActivity
import com.example.riotapi.Model.UserInfo
import com.example.riotapi.ViewModel.NickNameViewModel

import com.google.gson.Gson

class NickNameActivity : ComponentActivity() {

    private val nickNameViewModel by viewModels<NickNameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeNickNameScreen()
        }

        jsonParsing()
        summonerInfoObserve()

    }

    private fun summonerInfoObserve() {

        nickNameViewModel.summonerInfo.observe(this) {userDto ->
            syncUserInfo(userDto)
            moveToNextActivity()
        }

    }

    private fun moveToNextActivity() {
        startActivity(Intent(this , MainActivity::class.java))
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

    private fun jsonParsing() {
        championParsing()
        spellParsing()
    }


    @Composable
    fun ComposeNickNameScreen() {

        var (nickName : String, setNickName: (String) -> Unit) = remember{
            mutableStateOf("")
        }

        var (tagLine : String, setTagLine: (String) -> Unit) = remember{
            mutableStateOf("")
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            TextField(
                value = nickName,
                onValueChange = { setNickName },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                label = { Text("소환사 이름 입력") },

            )
            //소환사 이름 입력 EditText

            TextField(value = tagLine,
                onValueChange = { setTagLine },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                label = { Text("# 태그 라인 입력") },
            )

            Button(onClick = {
                    checkNickNameValid(nickName, tagLine)
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
