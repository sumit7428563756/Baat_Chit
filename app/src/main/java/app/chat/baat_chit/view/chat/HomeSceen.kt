package app.chat.baat_chit.view.chat

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.chat.baat_chit.R
import app.chat.baat_chit.data.model.User
import app.chat.baat_chit.data.model.sample
import app.chat.baat_chit.data.model.sampleList
import app.chat.baat_chit.navigation.Screens
import app.chat.baat_chit.ui.theme.PureWhite
import app.chat.baat_chit.ui.theme.Purple1
import app.chat.baat_chit.ui.theme.Purple80
import app.chat.baat_chit.ui.theme.gradientBrush
import app.chat.baat_chit.view.components.BoxIcon
import app.chat.baat_chit.view.components.ChatMessage
import app.chat.baat_chit.view.components.ContactListScreen
import app.chat.baat_chit.view.components.Searchbar
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(user: User, navController: NavController) {

    val minHeight = 450.dp
    val maxHeight = 650.dp

    var sheetHeight by remember { mutableStateOf(minHeight) }
    val animatedHeight by animateDpAsState(targetValue = sheetHeight, label = "sheetHeight")
    var showSheet by remember { mutableStateOf(false) }
    val state = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val dragState = rememberDraggableState { delta ->
        val newHeight = sheetHeight - delta.dp
        sheetHeight = newHeight.coerceIn(minHeight, maxHeight)
    }

    var searchText by remember { mutableStateOf("") }

    val filteredUsers = sampleList.filter {
        it.name.contains(searchText.trim(), ignoreCase = true)
    }

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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Img(user = user){
                        navController.navigate(Screens.Settings.route)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Good morning,", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                        Text(user.name, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
                BoxIcon(
                    icon = painterResource(id = R.drawable.plus),
                    tint = Color.Unspecified,
                    onClick = { showSheet = true }
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
            AllStories()
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .draggable(
                    orientation = Orientation.Vertical,
                    state = dragState,
                    onDragStopped = {
                        // Snap behavior
                        sheetHeight =
                            if (sheetHeight < (minHeight + maxHeight) / 2) minHeight else maxHeight
                    }
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(animatedHeight)
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(PureWhite)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 10.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = if (sheetHeight < (minHeight + maxHeight) / 2) R.drawable.up else R.drawable.down),
                            contentDescription = null,
                            tint = Purple80,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Text(
                            "Chat",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        Searchbar(text = searchText) {
                            searchText = it
                        }
                    }
                        Spacer(modifier = Modifier.height(20.dp))
                        ChatColumn(filteredUsers,  modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .background(PureWhite))
                    }
                }
            }

        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = { showSheet = false },
                sheetState = state,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                containerColor = Color.White,
            ) {
                 ContactListScreen()
            }
        }
    }
}



@Composable
fun Img(user: User,onImage: () -> Unit) {
    Box(
        modifier = Modifier
            .size(60.dp).clickable { onImage()  }
            .clip(CircleShape)
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = user.img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun MyStory() {
    Column(
        modifier = Modifier.padding(start = 20.dp, end = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(Purple80, CircleShape)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(painter = painterResource(id = R.drawable.user_plus),
                tint = Color.Unspecified,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .clickable { })
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Your status",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Color.White
        )
    }
}

@Composable
fun Stories(user: User) {
    Column(
        modifier = Modifier.padding(horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .border(4.dp, Color.White, CircleShape)
                .background(Purple80, CircleShape)
                .clip(CircleShape)
        ) {
            Icon(painter = rememberAsyncImagePainter(model = user.img),
                tint = Color.Unspecified,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .border(2.dp, Purple1, CircleShape)
                    .clickable { })
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = user.name,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Color.White
        )
    }
}

@Composable
fun AllStories() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        item {
            MyStory()
        }
        items(sampleList) {
            Stories(it)
        }
    }
}


@Composable
fun ChatColumn(user: List<User>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(user) {
            ChatItem(user = it) {

            }
        }
    }
}


@Composable
fun ChatItem(user: User, onClick: () -> Unit) {
    Box(modifier = Modifier
        .background(PureWhite)
        .fillMaxWidth()
        .clickable { onClick() }
        .height(90.dp), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = rememberAsyncImagePainter(model = user.img),
                    tint = Color.Unspecified,
                    contentDescription = "profile",
                    modifier = Modifier
                        .size(50.dp)
                        .clickable { }
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    user.name.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Clip,
                        )
                    }
                    user.lastmsg.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = Color.Gray,
                                fontWeight = FontWeight.W400,
                                fontSize = 14.sp,
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Clip
                        )
                    }
                }
                Column {
                    user.time.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.headlineSmall.copy(
                                color = Color.Black,
                                fontWeight = FontWeight.W400,
                                fontSize = 16.sp,
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Clip
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    ChatMessage(text = "7")
                }
            }
        }
    }
}

