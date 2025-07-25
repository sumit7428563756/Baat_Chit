package app.chat.baat_chit.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.chat.baat_chit.navigation.Screens
import app.chat.baat_chit.ui.theme.DeepPurple
import app.chat.baat_chit.ui.theme.Purple

@Composable
fun LoginButton(name : String,onClick : () -> Unit){
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Purple,
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth().height(50.dp)
            .padding(horizontal = 18.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = name,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}