import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import moe.tlaster.precompose.PreComposeApp
import network.QuizRepository

private val repository = QuizRepository()

@Composable
fun App() {
    //welcomeScreen()
    //scoreScreen("10 / 20")

    /*
     val testQuiz = Quiz(
         listOf(
             Question(
                 0,
                 "question 1",
                 0,
                 listOf(Answer(0, "answer 1"), Answer(2, "answer 2"))
             ),
             Question(
                 1,
                 "question 2",
                 1,
                 listOf(Answer(1, "answer 1"), Answer(2, "answer 2"))
             )
         )
     )
     */
    /*
    val questions = repository.questionState.collectAsState()

    if(questions.value.isNotEmpty()) {
        questionScreen(questions.value)
    }
    */
    PreComposeApp {
        MaterialTheme {
            rootNavHost()
        }
    }
}

expect fun getPlatformName(): String