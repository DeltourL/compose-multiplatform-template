package network

import network.data.Answer
import network.data.Question
import network.data.Quiz


class QuizMockData {
    private val mockQuiz = Quiz(
        listOf(
            Question(
                0,
                "What is the capital of Japan?",
                3,
                listOf(
                    Answer(0, "Seoul"),
                    Answer(1, "Beijing"),
                    Answer(2, "Tokyo"),
                    Answer(3, "Osaka")
                )
            ),
            Question(
                1,
                "Which planet is known as the Red Planet?",
                2,
                listOf(
                    Answer(0, "Venus"),
                    Answer(1, "Mars"),
                    Answer(2, "Jupiter"),
                    Answer(3, "Saturn")
                )
            ),
            Question(
                2,
                "Is the sun a star?",
                0,
                listOf(
                    Answer(0, "Yes"),
                    Answer(1, "No")
                )
            ),
            Question(
                3,
                "Who wrote 'Romeo and Juliet'?",
                1,
                listOf(
                    Answer(0, "Charles Dickens"),
                    Answer(1, "William Shakespeare"),
                    Answer(2, "Jane Austen"),
                    Answer(3, "Mark Twain")
                )
            ),
            Question(
                4,
                "What is the largest mammal on Earth?",
                2,
                listOf(
                    Answer(0, "Elephant"),
                    Answer(1, "Giraffe"),
                    Answer(2, "Blue Whale"),
                    Answer(3, "Hippopotamus")
                )
            )
        )
    )

    suspend fun getAllQuestions(): Quiz {
        return mockQuiz;
    }
}