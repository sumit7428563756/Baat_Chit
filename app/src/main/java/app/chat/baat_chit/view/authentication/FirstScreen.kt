package app.chat.baat_chit.view.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.chat.baat_chit.R
import app.chat.baat_chit.view.components.LoginButton
import app.chat.baat_chit.ui.theme.Purple
import app.chat.baat_chit.ui.theme.Purple1

@Composable
fun First(navController: NavController) {

    var number by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize().padding(top = 140.dp)
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier.height(200.dp).width(200.dp)) {
            Image(
                painter = painterResource(id = R.drawable.conversation),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(modifier = Modifier.height(70.dp).fillMaxWidth().padding(horizontal = 20.dp)) {
            Image(
                painter = painterResource(id = R.drawable.baat),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
            )
        }

        Text(
            text = "Enter Your Mobile Number",
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "We will send you a Authentication code",
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = number,
            onValueChange = { number = it },
            placeholder = { Text(text = "Enter Number") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = null
                )
            },
            maxLines = 10,
            minLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Purple1,
                unfocusedIndicatorColor = Purple1,
                focusedTextColor = Color.Black, unfocusedTextColor = Color.Black
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))
        LoginButton(name = "VERIFY") {
            navController.navigate("otpscreen_route/$number")
        }
    }
}

