package com.example.riotapi.LocalDB

import androidx.room.RoomDatabase

abstract class AppDatabase : RoomDatabase() {
    abstract fun dataDao() : DataDao
}