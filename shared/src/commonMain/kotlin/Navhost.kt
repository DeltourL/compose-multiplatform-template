
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import app.cash.sqldelight.db.SqlDriver
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.navigation.transition.NavTransition
import network.QuizRepository


@Composable
internal fun rootNavHost(driver: SqlDriver) {
    val quizRepository = QuizRepository(driver)

    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        navTransition = NavTransition(),
        initialRoute = "/welcome",
    ) {
        scene(
            route = "/welcome",
            navTransition = NavTransition(),
        ) {
            welcomeScreen(navigator)
        }
        scene(
            route = "/quiz",
            navTransition = NavTransition(),
        ) {
            val questions = quizRepository.questionState.collectAsState()

            if (questions.value.isNotEmpty()) {
                questionScreen(navigator, questions.value)
            } else {
                navigator.navigate(route = "/score/Error: Quiz not found")
            }
        }
        scene(
            route = "/score/{score}",
            navTransition = NavTransition(),
        ) { backStackEntry ->
            backStackEntry.path<String>("score")?.let { score ->
                scoreScreen(navigator, score)
            }
        }
    }
}
