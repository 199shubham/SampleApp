package com.aasan.sample.login

import android.app.Application
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.aasan.sample.base.AppNavigator
import com.aasan.sample.base.BaseViewModel
import com.aasan.sample.socket.RoomData
import com.aasan.sample.socket.SocketManager
import com.aasan.sample.splash.APIResponse
import com.aasan.sample.splash.data.*
import com.aasan.sample.splash.domain.SplashUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class LoginFragmentViewModel @Inject constructor(val app:Application,val useCase :SplashUseCase): BaseViewModel(app){

    lateinit var navigator: AppNavigator.LoginInNavigator
    val inputField = ObservableField<String>()
    var questionAnswerDataResponse = QuestionAnswerDataResponse(1,ArrayList<QAListResponse>())
    val profileDetails = ObservableField<List<QAListResponse>>()
    var qaList = ArrayList<QAListResponse>()
    var messageList = ArrayList<MessageX>()
    private val viewModelJob = Job()

    var result : LiveData<Response<DummySampleTest>> = MutableLiveData()

    private val uiState  = MutableLiveData<APIResponse<DummySampleTest>>()



    private val _workStatusData = MutableStateFlow(ChaGptDataResponse())
    /**
     * we can collect [WorkStatusResponse] as stateFlow into CheckListActivity, CompleteJobFragment and WorkOrderDetailActivity class
     * initialise with [_workStatusData]
     */
    val workStatusData: StateFlow<ChaGptDataResponse> = _workStatusData.asStateFlow()


    private val _users =MutableLiveData<Response<ChaGptDataResponse>>()
    val users: MutableLiveData<Response<ChaGptDataResponse>> get() = _users



    val onFieldDataChanged = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {

        }

    }

    init {
        inputField.addOnPropertyChangedCallback(onFieldDataChanged)
    }

    fun getSampleData(){
        viewModelScope.launch(Dispatchers.IO ) {
            kotlin.runCatching {
                messageList?.add(MessageX(inputField.get().toString(),"user"))

                useCase.getSampleData(ChatResponseDataModel(messageList!!,"gpt-3.5-turbo"))
            }.onSuccess {
                withContext(Dispatchers.Main){
                    try {
                        if(it.isSuccessful){
                            qaList.add(QAListResponse(inputField.get().toString(),it.body()?.choices?.get(0)?.message?.content?:""))

                            questionAnswerDataResponse?.qaListResponse = qaList
                            useCase.addCartService(questionAnswerDataResponse!!)

                        }
                        else{
                            navigator.showError(it?.message())
                        }
                    }

                    catch (e:java.lang.Exception){
                        System.out.println("shubhamerror" + e)

                    }
                }
            }.onFailure {
                withContext(Dispatchers.Main){
                    navigator.showProgress(false, null)
                    navigator.showError(it?.message?:"")
                }
            }


        }
    }

    fun getDummyData(){
        viewModelScope.launch(Dispatchers.IO ) {
            kotlin.runCatching {
                useCase.getDummyData()
            }.onSuccess {
                withContext(Dispatchers.Main){
                    try {

                    }
                    catch (e:java.lang.Exception){
                        System.out.println("shubhamerror" + e)

                    }
                }
            }.onFailure {
                withContext(Dispatchers.Main){
                    navigator.showProgress(false, null)
                    navigator.showError(it?.message?:"")
                }
            }


        }
    }

    fun fetchData(){
        viewModelScope.launch(Dispatchers.IO ) {
            kotlin.runCatching {
                useCase.getDummyData()
            }.onSuccess {
                withContext(Dispatchers.Main){
                    try {
                      uiState.postValue(it)
                    }

                    catch (e:java.lang.Exception){
                        System.out.println("shubhamerror" + e)

                    }
                }
            }.onFailure {
                withContext(Dispatchers.Main){
                    navigator.showProgress(false, null)
                    navigator.showError(it?.message?:"")
                }
            }


        }
    }

    fun  onClick(){
        getSampleData()
    }

    private fun getSocketData(){
        SocketManager.getSocket()?.on("new message"){data->
            // here we are receiving tht socket data part
        }
    }

    fun getCartStripData():LiveData<QuestionAnswerDataResponse>{
        return useCase.getAllCartServices()
    }


    fun getAllData():Flow<QuestionAnswerDataResponse>{
        return useCase.getAllCServices()
    }

    fun getUiState():MutableLiveData<APIResponse<DummySampleTest>>{
        return uiState
    }

    fun sendSocketData(){
        SocketManager.getSocket()?.emit("subscribe",Gson().toJson(
            RoomData("","","notification","")

        ))
    }

    /*override fun onCleared() {
        super.onCleared()
        SocketManager.getSocket()?.emit("unsubscribe","")
        viewModelJob.cancel()
    }*/


    fun getDataWithFlow() {
      messageList?.add(MessageX(inputField.get().toString(),"user"))
      /*viewModelScope.launch {
          when (val resumeJobResponse = useCase.getDataFromFlow(ChatResponseDataModel(messageList!!,"gpt-3.5-turbo"))) {
              // handle success response of feedback api
     is Response->
         _workStatusData.value = resumeJobResponse.body()!!
          }

      }*/


            viewModelScope.launch {
                useCase.getFlow(ChatResponseDataModel(messageList!!,"gpt-3.5-turbo")).catch { exception ->
                    // Handle error case
                    System.out.println(exception)
                }.collect { users ->
                    _users.value = users
                }
            }






}}