package com.aasan.sample.leaderly.repository

import com.aasan.joymoney.common.requests.BaseResponse
import com.aasan.sample.common.ApiEndPoints
import com.aasan.sample.leaderly.data.LeadRlyData
import com.aasan.sample.splash.data.ChaGptDataResponse
import com.aasan.sample.splash.data.ChatResponseDataModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface LeadRlyRepository {
    @GET(ApiEndPoints.NEW_API)
    suspend fun getLeadRlyModuleData(): Response<BaseResponse<List<LeadRlyData>>>
}