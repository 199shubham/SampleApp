package com.aasan.sample.splash.data

import com.aasan.sample.splash.APIResponse
import java.util.Objects

data class MockClass(
    val name :String,
    val address :String
)

fun toResponseData(mockClass: MockClass):APIResponse<DummySampleTest>{
    return APIResponse.Success(DummySampleTest(mockClass.name,mockClass.address))
}
