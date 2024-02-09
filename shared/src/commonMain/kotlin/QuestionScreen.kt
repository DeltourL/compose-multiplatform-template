import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.Navigator
import network.data.Question

@Composable
internal fun questionScreen(navigator: Navigator, questions: List<Question>) {
    var questionProgress by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf(-1L) }
    var score by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                modifier = Modifier.padding(all = 10.dp),
                text = questions[questionProgress].label,
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center
            )
        }

        Column {
            questions[questionProgress].answers.forEach { answer ->

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = (selectedAnswer == answer.id), onClick = {
                        selectedAnswer = answer.id
                    })
                    Text(answer.label)
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(onClick = {
                if (selectedAnswer == questions[questionProgress].correctAnswerId) {
                    score++
                }
                if (selectedAnswer != -1L) {
                    selectedAnswer = -1L

                    if (questions.size - 1 > questionProgress) {
                        questionProgress++
                    } else {
                        navigator.navigate("/score/$score out of ${questions.size}")
                    }
                }
            }) {
                Text("Next")
            }

            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth(),
                progress = (questionProgress.toFloat() + 1) / questions.size
            )
        }
    }
}