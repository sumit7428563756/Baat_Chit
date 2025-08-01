package app.chat.baat_chit.view.authentication

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import app.chat.baat_chit.R

import app.chat.baat_chit.data.model.Country
import app.chat.baat_chit.data.model.countries
import app.chat.baat_chit.ui.theme.Purple1


@SuppressLint("UnrememberedMutableState")

@Composable
fun CountryListScreen(
    navController: NavController,
    selectedCountry: MutableState<String>
) {
    val countryListVisible = remember { mutableStateOf(false) }
    val inputText = remember { mutableStateOf("") }
    val searchCountryList = remember { mutableStateOf(emptyList<Country>()) }

    val selectedParts = selectedCountry.value.split("[]")
    val currentCode = selectedParts.getOrNull(1) ?: "+1"

    countries.forEach {
        it.selected.value = (it.code == currentCode)
    }



    Column {
        Row(
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
                .background(Purple1),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navController.popBackStack() },
                shape = CircleShape, colors = ButtonDefaults.buttonColors(
                    containerColor = Purple1, contentColor = Color.White
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back2),
                    tint = Color.White,
                    contentDescription = null,
                    modifier = Modifier
                        .background(Purple1)
                        .size(30.dp)
                )
            }

        }
        AnimatedTopSearchBar(
            inputText = inputText,
            countryListVisible = countryListVisible,
            searchCountryList = searchCountryList,
        )
        if (countryListVisible.value) {
            SearchList(
                searchCountryList = searchCountryList,
                inputText = inputText,
                selectedCountry = selectedCountry,
                navController = navController
            )
        } else {
            CountryList(
                selectedCountry = selectedCountry,
                navController = navController
            )
        }
    }
}
