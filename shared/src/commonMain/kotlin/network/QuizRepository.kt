package network

import app.cash.sqldelight.db.SqlDriver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import network.data.Question


class QuizRepository(driver: SqlDriver) {

    private var quizDB = QuizDB(driver)
    private val quizAPI = QuizAPI()
    private val quizMockData = QuizMockData()
    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private val _questionState = MutableStateFlow(listOf<Question>())
    val questionState = _questionState

    init {
        updateQuiz()
    }

    private suspend fun fetchQuiz(): List<Question> = quizAPI.getAllQuestions().questions

    private suspend fun generateQuiz(): List<Question> = quizMockData.getAllQuestions().questions

    private suspend fun fetchAndStoreQuiz(): List<Question>{
        val questions  = fetchQuiz()
        quizDB.insertQuestions(questions)
        quizDB.setUpdateTimeStamp(Clock.System.now().epochSeconds)
        return questions
    }

    private fun updateQuiz() {
        coroutineScope.launch {
            _questionState.update {
                try {
                    val lastRequest = quizDB.getUpdateTimeStamp()
                    if(lastRequest == 0L || lastRequest - Clock.System.now().epochSeconds > 300000){
                        fetchAndStoreQuiz()
                    }else{
                        quizDB.getAllQuestions()
                    }
                } catch (e: NullPointerException) {
                    fetchAndStoreQuiz()
                } catch (e: Exception) {

                    e.printStackTrace()
                    generateQuiz()
                }
            }
        }
    }
}