package com.aasan.sample.splash.domain


import androidx.lifecycle.LiveData
import com.aasan.sample.splash.APIResponse
import com.aasan.sample.splash.data.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

import retrofit2.Response
import java.io.IOException


interface SplashUseCase {
    suspend fun getSampleData(chatRequestData:ChatResponseDataModel): Response<ChaGptDataResponse>

    suspend fun getDummyData():APIResponse<DummySampleTest>

    suspend fun addCartService(cartServices : QuestionAnswerDataResponse)

    fun getAllCartServices(): LiveData<QuestionAnswerDataResponse>

    fun getAllCServices(): Flow<QuestionAnswerDataResponse>

    suspend fun getDataFromFlow(chatRequestData:ChatResponseDataModel) : Response<ChaGptDataResponse>

    suspend fun getFlow(chatRequestData:ChatResponseDataModel) : Flow<Response<ChaGptDataResponse>>

}