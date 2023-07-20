package com.aasan.joymoney.common.requests

data class ValidateAadharRequest(
    val decentroTxnId: String,
    val otp: String
)