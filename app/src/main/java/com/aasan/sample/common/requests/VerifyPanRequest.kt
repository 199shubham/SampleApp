package com.aasan.joymoney.common.requests

data class VerifyPanRequest(
    val pancard: String
)

data class VerifyGstRequest(
    val gst: String
)