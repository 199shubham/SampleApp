package com.aasan.joymoney.common.requests

data class VerifyOtpRequest(
    val decentroTxnId: String,
    val otp: String
)