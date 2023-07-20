package com.aasan.joymoney.common.requests

data class AuthDeviceRequest(
    val deviceId: String,
    val latitude: String,
    val longitude: String
)