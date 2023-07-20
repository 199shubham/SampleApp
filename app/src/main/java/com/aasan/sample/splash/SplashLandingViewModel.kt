package com.aasan.sample.splash


import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aasan.sample.splash.data.*
import com.aasan.sample.splash.domain.SplashUseCase
import com.aasan.sample.base.AppNavigator
import com.aasan.sample.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashLandingViewModel @Inject constructor(
    val app: Application,
    val useCase: SplashUseCase
) : BaseViewModel(app) {
    lateinit var navigator: AppNavigator.SplashLandingNavigator

    lateinit var navigators: AppNavigator.CameraViewNavigator

    val dummyDataSet = ObservableField<SampleData>()

    private val uiState  = MutableLiveData<APIResponse<DummySampleTest>>()



    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = useCase.getDummyData()

            }
            catch (e:java.lang.Exception){
                e.printStackTrace()
            }
        }
    }

    /*fun getSampleData() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                useCase.getSampleData()
            }.onSuccess {
                withContext(Dispatchers.Main){
                    if(it.isSuccessful == true){
                        dummyDataSet.set(SampleData(it.body()!!.entries))

                    }else{
                        navigator.showError(it?.message()?:"")
                    }
                }
            }.onFailure {
                withContext(Dispatchers.Main){
                    navigator.showProgress(false, null)
                    navigator.showError(it.message?:"")
                }
            }
        }
    }*/
}