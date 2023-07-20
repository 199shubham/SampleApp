package com.aasan.sample.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aasan.sample.splash.data.QuestionAnswerDataResponse
import kotlinx.coroutines.flow.Flow


@Dao
interface SampleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEntry(entry:QuestionAnswerDataResponse)


    @Query("Select * from note_table")
    fun getAllEvents():LiveData<QuestionAnswerDataResponse>


    @Query("Select * from note_table")
    fun getAll():Flow<QuestionAnswerDataResponse>
}