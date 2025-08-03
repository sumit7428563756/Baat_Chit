package app.chat.baat_chit.view.authentication

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.chat.baat_chit.R
import app.chat.baat_chit.data.model.Country
import app.chat.baat_chit.data.model.countryCodeToFlagUrlMap
import app.chat.baat_chit.navigation.Screens
import app.chat.baat_chit.view.components.LoginButton
import app.chat.baat_chit.ui.theme.Purple
import app.chat.baat_chit.ui.theme.Purple1
import coil.compose.AsyncImage


@Composable
fun FirstScreen(
    navController: NavController,
    searchCountryList: MutableState<List<Country>>,
    selectedCountry: MutableState<String>
) {


    Column(
        Modifier
            .fillMaxSize()
    ) {
        First(
            searchCountryList = searchCountryList,
            navController = navController,
            selectedCountry = selectedCountry
        )
    }
}


@Composable
fun First(
    searchCountryList: MutableState<List<Country>>,
    navController: NavController,
    selectedCountry: MutableState<String>
) {

    var number by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    val countryListButton = rememberSaveable { mutableStateOf(true) }

    val selectedParts = selectedCountry.value.split("[]")
    val selectedName = selectedParts.getOrNull(0) ?: "United States"
    val selectedCode = selectedParts.getOrNull(1) ?: "+1"


    val flagUrl = if (selectedCode == "+1" && selectedName == "United States")
        "https://flagcdn.com/240x180/us.png"
    else
        countryCodeToFlagUrlMap[selectedCode.removePrefix("+").trim()]
            ?: "https://flagcdn.com/240x180/us.png"

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
                painter = painterResource(id = R.drawable.conversation),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
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
            onValueChange = {
                if (it.length <= 10 && it.all { char -> char.isDigit() }) {
                    number = it
                }
            },
            placeholder = { Text(text = "Enter Phone Number") },
            leadingIcon = {
                Row(
                    modifier = Modifier
                        .clickable(
                            enabled = countryListButton.value,
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }) {
                            navController.navigate(Screens.CountryListScreen.route) {
                                launchSingleTop = true
                            }
                        }
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        model = flagUrl,
                        contentDescription = "flag",
                        modifier = Modifier.size(30.dp),
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = selectedCode,
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .wrapContentWidth()
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.icon__9_),
                        tint = Color.Gray,
                        contentDescription = null,
                        modifier = Modifier
                            .size(15.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Purple1,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black, unfocusedTextColor = Color.Black
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(60.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))
        LoginButton(name = "VERIFY") {
            if (number.isEmpty()) {
                Toast.makeText(context, "Please fill the Number", Toast.LENGTH_SHORT).show()
            } else {
                navController.navigate("otpscreen_route/$number")
            }
        }
    }
}

