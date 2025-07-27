package app.chat.baat_chit.view.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.chat.baat_chit.R
import app.chat.baat_chit.navigation.Screens
import app.chat.baat_chit.ui.theme.Purple
import app.chat.baat_chit.ui.theme.Purple1
import app.chat.baat_chit.ui.theme.Purple80

@Composable
fun OtpScreen(navController: NavController,number : String) {

    val maskedNumber = maskPhoneNumber(number)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 140.dp)
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .width(200.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.otp),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize().padding(start = 40.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Enter Verification Code",
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "We have already sent the 6 digit code to\n $maskedNumber.please fill in this box below.",
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(30.dp))
        OtpInput {

        }
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Didn't received otp Yet?",
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            TextButton(onClick = { navController.navigate(Screens.Success.route) },contentPadding = PaddingValues(0.dp)) {
                Text(
                    text = "Resend!",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Purple1
                )
            }

        }

    }
}


fun maskPhoneNumber(number: String): String {
    return if (number.length >= 6) {
        val start = number.take(2)
        val end = number.takeLast(2)
        val stars = "*".repeat(number.length - 4)
        "$start$stars$end"
    } else {
        number // fallback if too short
    }
}

@Composable
fun OtpInput(
    otpLength: Int = 6,
    onOtpComplete: (String) -> Unit
) {
    val focusRequesters = remember {
        List(otpLength) { FocusRequester() }
    }

    val otpValues = remember {
        mutableStateListOf(*Array(otpLength) { "" })
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier.padding(10.dp)
    ) {
        for (i in 0 until otpLength) {

            val isFilled = otpValues[i].isNotEmpty()

            OutlinedTextField(
                value = otpValues[i],
                onValueChange = { value ->
                    if (value.length <= 1) {
                        otpValues[i] = value

                        // Move to next box automatically
                        if (value.isNotEmpty() && i < otpLength - 1) {
                            focusRequesters[i + 1].requestFocus()
                        }

                        // If last box filled
                        if (otpValues.all { it.length == 1 }) {
                            keyboardController?.hide()
                            onOtpComplete(otpValues.joinToString(""))
                        }
                    }
                },
                modifier = Modifier
                    .size(50.dp).clip(RoundedCornerShape(10.dp))
                    .focusRequester(focusRequesters[i])
                    .focusProperties {
                        // Prevent back-jumping
                        next =
                            if (i < otpLength - 1) focusRequesters[i + 1] else FocusRequester.Default
                        previous = if (i > 0) focusRequesters[i - 1] else FocusRequester.Default
                    },
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    color = Color.White
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword,
                    imeAction = if (i == otpLength - 1) ImeAction.Done else ImeAction.Next
                ),
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.colors(
                  unfocusedContainerColor = if (isFilled) Purple1 else Purple80,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedContainerColor = if (isFilled) Purple1 else Purple80,
                   focusedIndicatorColor  = if (isFilled) Purple1 else Purple80,
                    unfocusedIndicatorColor = if (isFilled) Purple1 else Purple80,
                    cursorColor = Color.White
                )
            )
        }
    }

    LaunchedEffect(Unit) {
        focusRequesters[0].requestFocus()
    }
}
