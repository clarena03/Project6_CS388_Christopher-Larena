package com.example.project6_cs388_christopherlarena

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WaterDao {
    @Query("SELECT * FROM water_table ORDER BY timestamp DESC")
    fun getAllEntries(): LiveData<List<WaterEntry>>

    @Insert
    suspend fun insert(entry: WaterEntry)

    @Query("DELETE FROM water_table")
    suspend fun deleteAll()
}
