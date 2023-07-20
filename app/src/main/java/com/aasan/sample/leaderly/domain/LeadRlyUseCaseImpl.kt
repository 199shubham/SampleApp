package com.aasan.sample.leaderly.domain


import androidx.lifecycle.LiveData
import com.aasan.joymoney.common.requests.BaseResponse
import com.aasan.sample.dao.SampleDao
import com.aasan.sample.leaderly.data.LeadRlyData
import com.aasan.sample.leaderly.repository.LeadRlyRepository
import com.aasan.sample.splash.APIResponse
import com.aasan.sample.splash.data.*
import com.aasan.sample.splash.repository.SampleDataRepository

import com.globalmed.corelib.repository.SharedPreferenceRepository
import retrofit2.Response

class LeadRlyUseCaseImpl(private val repository: LeadRlyRepository
): LeadRlyUseCase {
    override suspend fun getLeadRlyData(): Response<BaseResponse<List<LeadRlyData>>> {
        return repository.getLeadRlyModuleData()
    }


}