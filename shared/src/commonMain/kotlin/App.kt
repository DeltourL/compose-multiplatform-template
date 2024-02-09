import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import app.cash.sqldelight.db.SqlDriver
import com.myapplication.common.cache.Database
import moe.tlaster.precompose.PreComposeApp
import network.QuizRepository


@Composable
fun App(driver: SqlDriver) {
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
            rootNavHost(driver)
        }
    }
}

expect fun getPlatformName(): String

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}