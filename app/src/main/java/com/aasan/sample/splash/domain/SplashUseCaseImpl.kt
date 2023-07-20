package com.aasan.sample.splash.domain


import androidx.lifecycle.LiveData
import com.aasan.sample.dao.SampleDao
import com.aasan.sample.splash.APIResponse
import com.aasan.sample.splash.data.*
import com.aasan.sample.splash.repository.SampleDataRepository

import com.globalmed.corelib.repository.SharedPreferenceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


class SplashUseCaseImpl( private val repository: SampleDataRepository,private val sharedPreferenceRepository: SharedPreferenceRepository,
                      private val sampleDao : SampleDao
): SplashUseCase {




    override suspend fun getSampleData(chatRequest: ChatResponseDataModel): Response<ChaGptDataResponse> {
        return repository.getSampleData("Bearer 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",chatRequest)

    }

    override suspend fun getDummyData(): APIResponse<DummySampleTest> {
      return repository.getDummyData()
    }

    override suspend fun addCartService(cartServices: QuestionAnswerDataResponse) {
       sampleDao.addEntry(cartServices)
    }

    override fun getAllCartServices(): LiveData<QuestionAnswerDataResponse> {
       return sampleDao.getAllEvents()
    }


    override fun getAllCServices(): Flow<QuestionAnswerDataResponse> {
        return sampleDao.getAll()
    }

    override suspend fun getDataFromFlow(chatRequestData: ChatResponseDataModel): Response<ChaGptDataResponse> {
        return repository.getDataFromFlow("Bearer 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",chatRequestData)
    }

    override suspend fun getFlow(chatRequestData: ChatResponseDataModel): Flow<Response<ChaGptDataResponse>> = flow {
        val users = repository.getFlow("Bearer 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",chatRequestData)
        emit(users)
        return@flow
    }

}