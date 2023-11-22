package com.example.riotapi.LocalDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.riotapi.Data.UserInfo

@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(data : UserInfo)

    @Query("SELECT * FROM userInfo_table")
    fun getAllData() : UserInfo


}