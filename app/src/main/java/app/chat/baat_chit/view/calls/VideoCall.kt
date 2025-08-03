package app.chat.baat_chit.view.calls

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.chat.baat_chit.data.model.sampleList
import app.chat.baat_chit.view.components.CameraPreviewView

@Composable
fun VideoCall(user: String = "0", navController: NavController) {
    val uid = sampleList.find { it.uid == user }!!

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CameraPreviewView()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CallRow(user = uid, navController = navController)
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 50.dp)
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BoxCall(navController = navController)
        }
    }
}
