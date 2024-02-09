package network

import app.cash.sqldelight.async.coroutines.awaitAsList
import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import app.cash.sqldelight.db.SqlDriver
import com.myapplication.common.cache.Database
import network.data.Answer
import network.data.Question

class QuizDB(driver: SqlDriver) {

    private var database = Database(driver)
    private var queries = database.quizDatabaseQueries

    suspend fun getUpdateTimeStamp(): Long =
        queries.selectUpdateTimestamp().awaitAsOneOrNull()?.timestamprequest ?: 0L


    suspend fun setUpdateTimeStamp(timeStamp: Long) {
        queries.deleteTimeStamp()
        queries.insertTimeStamp(timeStamp)
    }

    suspend fun getAllQuestions(): List<Question> {
        return queries.selectAllQuestionsWithAnswers().awaitAsList()

            .groupBy { it.question_id }
            .map { (questionId, rowList) ->

                Question(
                    id = questionId,
                    label = rowList.first().label,
                    correctAnswerId = rowList.first().correctAnswerId,
                    answers = rowList.map { answer ->
                        Answer(
                            id = answer.id_,
                            label = answer.label_
                        )
                    }
                )
            }
    }


    suspend fun insertQuestions(questions: List<Question>) {
        queries.deleteQuestions();
        queries.deleteAnswers()
        questions.forEach { question ->
            queries.insertQuestion(question.id, question.label, question.correctAnswerId)
            question.answers.forEach { answer ->
                queries.insertAnswer(answer.id, answer.label, question.id)
            }
        }
    }
}