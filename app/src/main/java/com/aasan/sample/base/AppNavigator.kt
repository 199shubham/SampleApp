package com.aasan.sample.base



sealed interface AppNavigator{
    fun showError(_errorMsg: String)
    fun showProgress(_shouldShowProgress: Boolean, _progressText: String?)
    fun showToastMessage(msg: String)
    fun showDialogMessage(msg: String)

    interface SplashLandingNavigator:AppNavigator{
        fun onContinueClicked()
    }

    interface LoginInNavigator:AppNavigator{
        fun onContinueClicked()
    }


    interface SampleDetailsNavigator: AppNavigator {
        fun onContinue()

    }


        interface LeaderRlyNavigator: AppNavigator{
            fun onContinue()


    }

    interface CameraViewNavigator: AppNavigator{
        fun onCameraViewClick()

        fun onCameraImageReceived()


    }

























}