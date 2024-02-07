import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.navigation.Navigator

@Composable
internal fun welcomeScreen(navigator: Navigator) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Welcome", style = MaterialTheme.typography.h1)
                Text("A simple quizz to discover KMP, KMM and compose", style = MaterialTheme.typography.body1)
                Button(onClick = { navigator.navigate(route = "/quiz") }) {
                    Text("Start the quizz")
                }
            }
        }
    }
}