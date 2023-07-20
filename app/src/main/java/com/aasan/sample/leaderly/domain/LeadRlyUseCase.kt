package com.aasan.sample.leaderly.domain


import androidx.lifecycle.LiveData
import com.aasan.joymoney.common.requests.BaseResponse
import com.aasan.sample.leaderly.data.LeadRlyData
import com.aasan.sample.splash.APIResponse
import com.aasan.sample.splash.data.*

import retrofit2.Response

interface LeadRlyUseCase {
    suspend fun getLeadRlyData(): Response<BaseResponse<List<LeadRlyData>>>
}