package app.chat.baat_chit.view.calls

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import app.chat.baat_chit.R
import app.chat.baat_chit.data.model.User
import app.chat.baat_chit.data.model.sampleList
import app.chat.baat_chit.navigation.Screens
import app.chat.baat_chit.ui.theme.PureWhite
import app.chat.baat_chit.ui.theme.Purple1
import app.chat.baat_chit.ui.theme.Purple80
import app.chat.baat_chit.view.chat.ChatColumn
import app.chat.baat_chit.view.chat.Img
import app.chat.baat_chit.view.components.BoxIcon
import app.chat.baat_chit.view.components.BoxIcon2
import app.chat.baat_chit.view.components.ChatMessage
import app.chat.baat_chit.view.components.ContactListCallScreen
import app.chat.baat_chit.view.components.ContactListScreen
import app.chat.baat_chit.view.components.Searchbar
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calls(navController: NavController) {

    var searchText by remember { mutableStateOf("") }
    var sheet by remember { mutableStateOf(false) }
    val filteredUsers = sampleList.filter {
        it.name.contains(searchText.trim(), ignoreCase = true)
    }
    val state = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Purple1)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Calls",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                BoxIcon(
                    icon = painterResource(id = R.drawable.plus),
                    tint = Color.Unspecified,
                    onClick = { sheet = true }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Searchbar(text = searchText) {
                    searchText = it
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(PureWhite)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 30.dp)
            ) {
                CallColumn(user = filteredUsers, modifier = Modifier.fillMaxWidth())
            }
        }

        if (sheet) {
            ModalBottomSheet(
                onDismissRequest = { sheet = false },
                sheetState = state,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                containerColor = Color.White,
            ) {
                ContactListCallScreen()
            }
        }

    }
}


@Composable
fun CallColumn(user: List<User>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(
                "Recent",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        items(user) {
            CallItem(user = it) {

            }
        }
    }
}

@Composable
fun CallItem(user: User, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(PureWhite)
            .fillMaxWidth()
            .clickable { onClick() }
            .height(90.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Profile Image
            Icon(
                painter = rememberAsyncImagePainter(model = user.img),
                tint = Color.Unspecified,
                contentDescription = "profile",
                modifier = Modifier
                    .size(50.dp)
                    .clickable { }
            )

            Spacer(modifier = Modifier.width(20.dp))


            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // User name
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                // Call and video icons
                Row {
                    BoxIcon2(
                        icon = painterResource(id = R.drawable.video),
                        tint = Color.White
                    ) {}

                    Spacer(modifier = Modifier.width(10.dp))

                    BoxIcon2(
                        icon = painterResource(id = R.drawable.calls),
                        tint = Color.White
                    ) {

                    }
                }
            }
        }
    }
}




