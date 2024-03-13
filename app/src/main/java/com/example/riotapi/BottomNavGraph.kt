package com.example.riotapi

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.ChampEx.route
    ){

        composable(route = BottomBarScreen.ChampEx.route){
            ChampExScreen()
        }

        composable(route = BottomBarScreen.FightRecord.route){
            FightRecordScreen()
        }


    }
}