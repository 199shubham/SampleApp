package com.aasan.sample.splash.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Objects


@Entity (tableName = "note_table")
data class QuestionAnswerDataResponse(
    @PrimaryKey(autoGenerate = true)
    var values :Int=1,
    var qaListResponse: List<QAListResponse>?=null

)


