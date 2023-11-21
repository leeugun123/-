package com.example.riotapi.LocalDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataDao {

    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    //fun insertData(data : List<DataModel>)

   // @Query("SELECT * FROM DataModel")
    //fun getAllData() : List<DataModel>


}