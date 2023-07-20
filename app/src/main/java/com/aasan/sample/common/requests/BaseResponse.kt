package com.aasan.joymoney.common.requests

data class BaseResponse<T>(val code: Int, val message: String?, val data: T?)
