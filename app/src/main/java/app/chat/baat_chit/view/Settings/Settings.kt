package app.chat.baat_chit.view.Settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.chat.baat_chit.R
import app.chat.baat_chit.data.model.User
import app.chat.baat_chit.data.model.sample
import app.chat.baat_chit.ui.theme.Purple1
import app.chat.baat_chit.ui.theme.Purple80
import app.chat.baat_chit.view.chat.Img
import app.chat.baat_chit.view.components.BoxIcon
import coil.compose.rememberAsyncImagePainter

@Composable
fun Settings(navController: NavController, user: User) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Purple1)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .border(
                        6.dp, Color.White,
                        CircleShape
                    )
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = user.img),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            TextButton(onClick = { }) {
                Text(
                    text = "Edit",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Purple80
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            EditText(user = user)
            Spacer(modifier = Modifier.height(20.dp))
            EditText2(user = user)
            Spacer(modifier = Modifier.height(20.dp))
            EditText3(user = user)
            Spacer(modifier = Modifier.height(40.dp))
            TextButton(onClick = { }) {
                Text(
                    text = "Delete Account",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
            }
        }
    }

}


@Composable
fun EditText(user: User) {
    var text by remember { mutableStateOf(user.name.toString()) }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
        },
                placeholder = {
                Text(
                    user.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
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
            focusedTextColor = Purple1,
            unfocusedTextColor = Purple1,
            focusedContainerColor = Color.White,
            errorContainerColor = Color.Red,
            unfocusedContainerColor = Color.White,
            unfocusedIndicatorColor = Purple1,
            focusedIndicatorColor = Purple1,
            cursorColor = Purple1,
            focusedSupportingTextColor = Purple80,
            unfocusedSupportingTextColor = Purple80,
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


@Composable
fun EditText2(user: User) {
    var text by remember { mutableStateOf(user.about.toString()) }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.about),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
        },
        placeholder = {
                Text(
                    user.about,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
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
            focusedTextColor = Purple1,
            unfocusedTextColor = Purple1,
            focusedContainerColor = Color.White,
            errorContainerColor = Color.Red,
            unfocusedContainerColor = Color.White,
            unfocusedIndicatorColor = Purple1,
            focusedIndicatorColor = Purple1,
            cursorColor = Purple1,
            focusedSupportingTextColor = Purple80,
            unfocusedSupportingTextColor = Purple80,
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


@Composable
fun EditText3(user: User) {
    var text by remember { mutableStateOf(user.phone.toString()) }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        value = text,
        onValueChange = { },
        enabled = false,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.calls),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
        },
        placeholder = {
                Text(
                    user.phone,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
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
            focusedTextColor = Purple1,
            unfocusedTextColor = Purple1,
            focusedContainerColor = Color.White,
            errorContainerColor = Color.Red,
            unfocusedContainerColor = Color.White,
            unfocusedIndicatorColor = Purple1,
            focusedIndicatorColor = Purple1,
            cursorColor = Purple1,
            focusedSupportingTextColor = Purple80,
            unfocusedSupportingTextColor = Purple80,
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