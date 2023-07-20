package com.aasan.sample.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aasan.sample.splash.converter.CartConverter
import com.aasan.sample.splash.data.QuestionAnswerDataResponse


@Database(
    entities = [QuestionAnswerDataResponse::class],

    version = 5,
    exportSchema = false

)

@TypeConverters(CartConverter::class)

 abstract class SampleDatabase :RoomDatabase(){

     abstract fun getNoteDao() :SampleDao

     companion object{
         const val DB_NAME = "note_database.db"
     }
}