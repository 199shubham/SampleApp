package com.aasan.joymoney.common.requests

data class GenerateAadharOtpRequest(
    val aadhaarNumber: String,
    val captcha: String,
    val decentroTxnId: String
)