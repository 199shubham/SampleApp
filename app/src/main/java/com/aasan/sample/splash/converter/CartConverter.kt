package com.aasan.sample.splash.converter

import androidx.room.TypeConverter
import com.aasan.sample.splash.data.QAListResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartConverter {

    private val gson by lazy { Gson() }


    @TypeConverter
    fun toQAListData(str :String) : List<QAListResponse>?{
        val type = object :TypeToken<List<QAListResponse>>(){}.type
        return gson.fromJson(str,type)
    }

    @TypeConverter
    fun fromQAListData(choiceList: List<QAListResponse>?):String?{
        val type = object :TypeToken<List<QAListResponse>>(){}.type
        return gson.toJson(choiceList,type)
    }
}