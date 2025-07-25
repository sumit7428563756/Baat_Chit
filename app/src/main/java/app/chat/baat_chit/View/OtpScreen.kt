package app.chat.baat_chit.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.chat.baat_chit.R
import app.chat.baat_chit.ui.theme.BluePrimary

@Composable
fun OtpScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Enter",
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.W800,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "OTP",
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                fontWeight = FontWeight.W800,
                color = BluePrimary
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "We have already sent the 6 digit code to \n .please fill in the box below",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))

    }
}