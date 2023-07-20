package com.aasan.sample.leaderly

import android.app.Application
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aasan.sample.base.AppNavigator
import com.aasan.sample.base.BaseViewModel
import com.aasan.sample.leaderly.domain.LeadRlyUseCase
import com.aasan.sample.socket.RoomData
import com.aasan.sample.socket.SocketManager
import com.aasan.sample.splash.APIResponse
import com.aasan.sample.splash.data.*
import com.aasan.sample.splash.domain.SplashUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class LeaderRlyViewModel @Inject constructor(val app:Application, val useCase :LeadRlyUseCase): BaseViewModel(app){

    lateinit var navigator: AppNavigator.LeaderRlyNavigator
    val inputField = ObservableField<String>()



    val onFieldDataChanged = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {

        }

    }

    init {
        inputField.addOnPropertyChangedCallback(onFieldDataChanged)
    }


}