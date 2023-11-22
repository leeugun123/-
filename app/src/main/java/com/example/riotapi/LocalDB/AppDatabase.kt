package com.example.riotapi.LocalDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.riotapi.Data.UserInfo


@Database(entities = [UserInfo::class] , version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataDao() : DataDao
}