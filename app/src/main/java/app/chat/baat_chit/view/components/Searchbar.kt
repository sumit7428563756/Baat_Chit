package app.chat.baat_chit.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import app.chat.baat_chit.R
import app.chat.baat_chit.ui.theme.Purple1
import app.chat.baat_chit.ui.theme.Purple80


@Composable
fun Searchbar( text: String,
               onTextChange: (String) -> Unit) {


    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    Column {
        OutlinedTextField(
            value = text,
            onValueChange =  onTextChange ,
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
            placeholder = { Text("Search", color = Color.Gray) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    tint = Color.Gray,
                    contentDescription = null
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.mic),
                    tint = Color.Gray,
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
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
            singleLine = true,
            shape = RoundedCornerShape(15.dp)
        )
    }
}




@Composable
fun Searchbar2( text: String,
               onTextChange: (String) -> Unit) {


    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    Column {
        OutlinedTextField(
            value = text,
            onValueChange =  onTextChange ,
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
            placeholder = { Text("Search name or number", color = Color.Gray) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    tint = Color.Gray,
                    contentDescription = null
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.mic),
                    tint = Color.Gray,
                    contentDescription = null
                )
            },
            modifier = Modifier
                .fillMaxWidth().imePadding()
                .focusRequester(focusRequester),
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
            singleLine = true,
            shape = RoundedCornerShape(15.dp)
        )
    }
}


