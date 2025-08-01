package app.chat.baat_chit.view.account

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.chat.baat_chit.navigation.Screens
import app.chat.baat_chit.ui.theme.Purple1
import app.chat.baat_chit.ui.theme.Purple80
import app.chat.baat_chit.view.components.LoginButton


@Composable
fun Name(navController: NavController) {

    var text  = remember { mutableStateOf("") }
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
        // Top content
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "What's your name?",
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Purple1
            )
            Spacer(modifier = Modifier.height(20.dp))
            NameText(text = text)
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .padding(bottom = 10.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginButton(name = "Next") {
                if (text.value.isEmpty()) {
                    Toast.makeText(context, "Please Enter Your Name", Toast.LENGTH_SHORT).show()
                } else {
                    navController.navigate(Screens.UploadPhoto.route)
                }
            }
        }
    }
}




@Composable
fun NameText( text : MutableState<String>) {

    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        value = text.value,
        onValueChange = { text.value = it },
        placeholder = {
            Text(
                text = "Enter your name",
                color = Purple80
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Purple1,
            unfocusedIndicatorColor = Purple1,
            cursorColor = Color.Black
        ),
        shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Black
        ),
        minLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .focusRequester(focusRequester)
    )
}