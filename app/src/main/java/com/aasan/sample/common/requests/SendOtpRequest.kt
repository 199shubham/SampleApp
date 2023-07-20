package com.aasan.joymoney.common.requests

data class SendOtpRequest(
    val mobile: String,
    val simSlot: Int,
    val whatsAppConsent: Boolean
)