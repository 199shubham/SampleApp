package com.aasan.joymoney.common.requests

data class CreateUserRequest(
    val businessName: String?,
    val businessPAN: String?,
    val companyType: String?,
    val dob: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val gst: String?,
    val lastName: String,
    val maritalStatus: String,
    val middleName: String?,
    val monthlyIncome: Int,
    val monthlySales: Int?,
    val natureOfBusiness: String?,
    val occupationType: String,
    val residence: String,
    val selfie: String?,
    val shopType: String?,
    val title: String
)