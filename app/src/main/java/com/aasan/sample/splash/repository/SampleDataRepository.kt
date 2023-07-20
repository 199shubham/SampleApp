package com.aasan.sample.splash.repository

import com.aasan.sample.common.ApiEndPoints
import com.aasan.sample.splash.APIResponse
import com.aasan.sample.splash.data.ChaGptDataResponse
import com.aasan.sample.splash.data.ChatResponseDataModel
import com.aasan.sample.splash.data.DummySampleTest
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.*

interface SampleDataRepository {
    @POST(ApiEndPoints.SAMPLE_DATA)
    suspend fun getSampleData(@Header("Authorization")_token:String,@Body body: ChatResponseDataModel): Response<ChaGptDataResponse>


    @POST(ApiEndPoints.SAMPLE_DATA)
    suspend fun getDataFromFlow(@Header("Authorization")_token:String,@Body body: ChatResponseDataModel): Response<ChaGptDataResponse>

    @GET(ApiEndPoints.DUMMY_DATA)
    suspend fun getDummyData(): APIResponse<DummySampleTest>


    @POST(ApiEndPoints.SAMPLE_DATA)
    suspend fun getFlow(@Header("Authorization")_token:String,@Body body: ChatResponseDataModel): Response<ChaGptDataResponse>

}