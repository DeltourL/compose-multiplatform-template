package com.example.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Quiz(var questions: List<Question>) {}

@Serializable
data class Question(
    val id: Int,
    val label: String,
    @SerialName("correct_answer_id") val correctAnswerId: Int,
    val answers: List<Answer>
) {}

@Serializable
data class Answer(
    val id: Int,
    val label: String
) {}