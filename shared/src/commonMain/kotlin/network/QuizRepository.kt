package network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import network.data.Question


class QuizRepository() {

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

    private fun updateQuiz() {
        coroutineScope.launch {
            try {
                _questionState.update { fetchQuiz() }
            } catch (exception: Exception) {
                _questionState.update { generateQuiz() }
            }
        }
    }
}