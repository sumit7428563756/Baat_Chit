package app.chat.baat_chit.view.authentication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import app.chat.baat_chit.R
import app.chat.baat_chit.data.model.Country
import app.chat.baat_chit.data.model.countries
import app.chat.baat_chit.data.model.countryCodeToFlagUrlMap
import app.chat.baat_chit.ui.theme.Purple1
import app.chat.baat_chit.ui.theme.Purple80
import coil.compose.AsyncImage



fun search(
    countryListVisible: MutableState<Boolean>,
    value: String,
    countries: List<Country>,
    searchCountryList: MutableState<List<Country>>
) {
    val searchList = mutableListOf<Country>()
    countries.filter { it.name.contains(value, true) || it.code.contains(value, true) }
        .forEach {
            searchList.add(it)
        }
    searchCountryList.value = searchList
    countryListVisible.value = true
}

@Composable
fun CountryItem(
    name: String = "name",
    code: String = "+ code",
    selected: Boolean = false,
    onClick: (String, String) -> Unit = { _, _ -> }
) {
    var countryFlag = countryCodeToFlagUrlMap[code.removePrefix("+").trim()]
    if (code == "+1" && name == "United States")
        countryFlag = "https://flagcdn.com/240x180/us.png"

    Column(
        Modifier.padding(start = 6.dp, end = 6.dp)
    ) {
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.Gray.copy(0.4f),
            thickness = 0.6.dp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(name, code) },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.height(48.dp), verticalAlignment = Alignment.CenterVertically) {
                Box {
                    var isLoading by remember { mutableStateOf(true) }
                    AsyncImage(
                        model = countryFlag ?: "https://flagcdn.com/240x180/us.png",
                        contentDescription = "flag",
                        modifier = Modifier.size(35.dp),
                        onLoading = { isLoading = true },
                        onSuccess = { isLoading = false },
                        onError = { isLoading = false }
                    )
                    if (isLoading)
                        CircularProgressIndicator(Modifier.size(35.dp))
                }

                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = name,
                    style = TextStyle(
                        color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                    )
                )
            }
            Row(modifier = Modifier.height(48.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(code)
                if (selected) {
                    Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = "Tick Mark",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}


@Composable
fun CountryList(
    selectedCountry: MutableState<String>,
    navController: NavController
) {
    LazyColumn {
        items(countries) { country ->
            var selected by remember { country.selected }
            CountryItem(
                name = country.name,
                code = country.code,
                selected = selected
            ) { selectedName, selectedCode ->
                countries.forEach { it.selected.value = false }
                selected = true
                selectedCountry.value = "$selectedName[]$selectedCode"
                navController.popBackStack()
            }

        }
        }
    }


@Composable
fun SearchList(
    searchCountryList: MutableState<List<Country>>,
    inputText: MutableState<String>,
    selectedCountry: MutableState<String>,
    navController: NavController
) {
    if (searchCountryList.value.isEmpty()) {
        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
                append("No Country Found with this ")
            }
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                append(inputText.value)
            }
            withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
                append(" name")
            }
        }
        Text(text = annotatedString)
    } else
        LazyColumn {
            items(searchCountryList.value) { country ->
                var selected by remember { country.selected }
                CountryItem(
                    name = country.name,
                    code = country.code,
                    selected = selected
                ) { selectedName, selectedCode ->
                    countries.forEach { it.selected.value = false }
                    selected = true
                    selectedCountry.value = "$selectedName[]$selectedCode"
                    navController.popBackStack()
                }

            }
        }
}


@Composable
fun AnimatedTopSearchBar(
    inputText: MutableState<String>,
    countryListVisible: MutableState<Boolean>,
    searchCountryList: MutableState<List<Country>>,
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(inputText.value) {
        val text = inputText.value.trim()
        if (text.isNotEmpty()) {
            search(countryListVisible, text, countries, searchCountryList)
        } else {

            countryListVisible.value = false
        }
    }

    TextField(
        value = inputText.value,
        onValueChange = { inputText.value = it },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search),
                tint = Color.Unspecified,
                contentDescription = null
            )
        },
        placeholder = { Text("Search Country", color = Color.White) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp)
            .focusRequester(focusRequester),
        singleLine = true,
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Purple1,
            unfocusedTextColor = Purple1,
            focusedContainerColor = Purple80,
            errorContainerColor = Color.Red,
            unfocusedContainerColor = Purple80,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            cursorColor = Purple1,
            focusedSupportingTextColor = Color.White,
            unfocusedSupportingTextColor = Color.White,
        )
    )
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTopBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    navigationIcon: ImageVector? = null,
    actionsIcon: ImageVector? = null,
    topAppBarScrollBehavior: TopAppBarScrollBehavior,
    onNavigation: () -> Unit = { },
    onActions: () -> Unit = { },
) {

    TopAppBar(
        modifier = modifier,
        title = title,
        navigationIcon = {
            if (navigationIcon != null)
                IconButton(onClick = { onNavigation() }) {
                    Icon(navigationIcon, "navigationIcon")
                }
        },
        actions = {
            if (actionsIcon != null)
                IconButton(onClick = { onActions() }) {
                    Icon(actionsIcon, "actionsIcon")
                }
        },
        scrollBehavior = topAppBarScrollBehavior
    )
}