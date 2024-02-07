package com.example.routes

import com.example.models.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val quiz = Quiz(
    listOf(
        Question(
            id = 0,
            label = "Which programming language is known for its simplicity and readability?",
            correctAnswerId = 1,
            answers = listOf(
                Answer(id = 0, label = "Java"),
                Answer(id = 1, label = "Python"),
                Answer(id = 2, label = "C++"),
                Answer(id = 3, label = "JavaScript")
            )
        )
    )
)

fun Route.quizRouting() {
    route("/quiz") {
        get {
            call.respond(quiz)
        }
    }
}
