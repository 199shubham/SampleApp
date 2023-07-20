package com.aasan.sample.leaderly.data

data class LeadRlyDataItem(
    val browseBy: String,
    val description: String,
    val enabled: Boolean,
    val feedback: Feedback,
    val image: String,
    val learningObjectives: List<String>,
    val moduleName: String,
    val moreCategory: String,
    val questionsData: List<QuestionsData>,
    val responses: Responses,
    val tag: List<Tag>,
    val timeStamp: Int
)