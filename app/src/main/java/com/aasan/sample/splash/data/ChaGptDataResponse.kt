package com.aasan.sample.splash.data

import com.google.gson.annotations.SerializedName


data class ChaGptDataResponse(
    @SerializedName("created") val created: Int,
    @SerializedName("id") val id: String,
    @SerializedName("model") val model: String,
    @SerializedName("value") val value: String,
    @SerializedName("usage") val usage: Usage?,
    @SerializedName("choices") val choices: List<Choice>?,
    )

{
    // No-args constructor
    constructor() : this(0, "","","",null,null)
}
