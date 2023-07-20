package com.aasan.sample.leaderly.data

data class QuestionsData(
    val answers: List<Answer>,
    val channelContent: String,
    val channelUid: String,
    val correctAnswer: Int,
    val moment: String,
    val question: String,
    val relatedLearningType: String
)