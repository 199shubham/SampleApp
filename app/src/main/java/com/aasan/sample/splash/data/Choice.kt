package com.aasan.sample.splash.data

import java.util.Objects

data class Choice(
    val finish_reason : String ,
    val index :Int = 0,
    val message :Message
)
