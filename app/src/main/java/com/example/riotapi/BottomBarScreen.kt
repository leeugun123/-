package com.example.riotapi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route : String,
    val title : String,
    val icon : ImageVector
){
    object ChampEx : BottomBarScreen(
        route = "champEx",
        title = "ChampEx",
        icon = Icons.Default.Home
    )

    object FightRecord : BottomBarScreen(
        route = "fightRecord",
        title = "FightRecord",
        icon = Icons.Default.Favorite
    )

}
