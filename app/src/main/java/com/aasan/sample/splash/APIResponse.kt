package com.aasan.sample.splash

import com.aasan.sample.splash.data.DummySampleTest

sealed class APIResponse <out T>{
    data class Success<out T>(val data :DummySampleTest):APIResponse<T>()

    data class Error (val error :String) :APIResponse<Nothing>()
}
