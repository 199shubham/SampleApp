package com.aasan.joymoney.common.requests

data class StoreSmsRequestItemRequest(
    val from: String,
    val message: String,
    val messageUId: String,
    val time: Long
)