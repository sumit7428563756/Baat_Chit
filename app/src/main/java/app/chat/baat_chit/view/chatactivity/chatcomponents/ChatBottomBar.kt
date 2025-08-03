package app.chat.baat_chit.view.chatactivity.chatcomponents

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star

import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.chat.baat_chit.R
import app.chat.baat_chit.data.model.User
import app.chat.baat_chit.ui.theme.PureWhite
import app.chat.baat_chit.ui.theme.Purple1
import app.chat.baat_chit.view.chatactivity.chatscreens.BtmSheet
import app.chat.baat_chit.view.chatactivity.chatscreens.btnSheet
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatBottomBar(
    modifier: Modifier = Modifier,
    showAttachmentRow: MutableState<Boolean> = remember { mutableStateOf(false) },
    onSend: (String) -> Unit = {},
    onMicRecord: () -> Unit = {}
) {
    val inputText = remember { mutableStateOf("") }
//    val showEmojiPicker = rememberEmojiPickerLauncher()
    val scope = rememberCoroutineScope()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .imePadding(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(50),
            tonalElevation = 2.dp,
            color = Color.White //
        ) {
            TextField(
                value = inputText.value,
                onValueChange = { inputText.value = it },
                maxLines = 6,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeightIn(min = 50.dp, max = 150.dp),
                placeholder = {
                    Text(
                        text = "Message",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Gray
                        )
                    )
                },
//                leadingIcon = {
//                    IconButton(
//                        onClick = {
//                            scope.launch {
//                                val result = showEmojiPicker()
//                                result?.let {
//                                    inputText.value += it
//                                }
//                            }
//                        },
//                        modifier = Modifier.size(25.dp)
//                    ) {
//                        Image(
//                            painter = painterResource(id = R.drawable.ic_emojis),
//                            contentDescription = "Emojis Picker",
//                            modifier = Modifier.size(40.dp)
//                        )
//                    }
//                },
                trailingIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                showBottomSheetDialog()
                                showAttachmentRow.value = !showAttachmentRow.value
                            },
                            modifier = Modifier.size(45.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.attach),
                                contentDescription = "Attach",
                                contentScale = ContentScale.FillHeight
                            )
                        }

                        IconButton(
                            onClick = {
                                // Camera logic
                            },
                            modifier = Modifier.size(25.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.camera),
                                contentDescription = "Camera",
                                modifier = Modifier.size(45.dp)
                            )
                        }

                        Spacer(Modifier.width(8.dp))
                    }
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                ),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                ),
                shape = RoundedCornerShape(50)
            )
        }

        Spacer(Modifier.width(8.dp))

        Surface(
            color = Color.White,
            shape = CircleShape
        ) {
            IconButton(
                modifier = Modifier.clip(CircleShape),
                onClick = {
                    if (inputText.value.isNotBlank()) {
                        onSend(inputText.value.trim())
                        inputText.value = ""
                    } else {
                        onMicRecord()
                    }
                }
            ) {
                if (inputText.value.isEmpty()) {
                    Image(
                        painter = painterResource(id = R.drawable.mic),
                        contentDescription = "Record",
                        modifier = Modifier.size(24.dp),
                        contentScale = ContentScale.Fit,
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.send),
                        contentDescription = "Send",
                        modifier = Modifier.size(24.dp),
                        contentScale = ContentScale.Fit,
                    )
                }
            }
        }
    }
}

fun showBottomSheetDialog() {

}

data class Contact(
    val name: String,
    val profilePicture: String,
    val lastSeen: String
)

@Preview
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(
    navHostController: NavHostController = rememberNavController(),
    contact: Contact = Contact("John Doe", "https://picsum.photos/200/300", "12:00 PM"),
    onBackClick: () -> Unit = { navHostController.popBackStack() },
    onCallClick: () -> Unit = {},
    onVideoCallClick: () -> Unit = {},
    onMoreClick: () -> Unit = {},
) {
    val name = contact.name
    val image = contact.profilePicture ?: "https://picsum.photos/200/300"
    val lastSeen = contact.lastSeen

    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onBackClick,
                colors = if (isSystemInDarkTheme()) {
                    IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)
                } else {
                    IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onSurface)
                }
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
        title = {
            Row(
                Modifier.clickable {
                    navHostController.navigate("Screens.ProfileScreen.route") {
                        launchSingleTop = true
                    }
                }
            ) {
                if (image.isNotEmpty()) {
                    AsyncImage(
                        model = image,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Icon(
                        imageVector = Icons.Rounded.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        tint = if (isSystemInDarkTheme()) {
                            MaterialTheme.colorScheme.onPrimary.copy(0.5f)
                        } else {
                            MaterialTheme.colorScheme.onSurface.copy(0.5f)
                        }
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Column {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium,
                        color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = lastSeen,
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        },
        actions = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onVideoCallClick,
                    colors = if (isSystemInDarkTheme()) {
                        IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)
                    } else {
                        IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onSurface)
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Call, contentDescription = "Video Call")
                }
                IconButton(
                    onClick = onCallClick,
                    colors = if (isSystemInDarkTheme()) {
                        IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)
                    } else {
                        IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onSurface)
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Phone, contentDescription = "Phone Call")
                }
                IconButton(
                    onClick = onMoreClick,
                    colors = if (isSystemInDarkTheme()) {
                        IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onPrimary)
                    } else {
                        IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onSurface)
                    }
                ) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Options")
                }
            }
        }
    )
}

@Composable
fun DateStamp(modifier: Modifier = Modifier, s: String = "Today") {
    Surface(
        modifier,
        color = MaterialTheme.colorScheme.surface.copy(0.5f),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.onSurface.copy(0.5f))
    ) {
        Text(
            text = s,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(4.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Composable
fun ChatBubble(
    modifier: Modifier = Modifier,
    text: String = "Hello",
    isSender: Boolean = true,
    isFirstMessage: Boolean = true,
    time: String = "12:00 PM",
) {
    val topEnd = if (isSender) 20.dp else {
        if (isFirstMessage) 0.dp else 20.dp
    }
    val bottomEnd = if (isSender) 20.dp else 30.dp
    val bottomStart = if (isSender) {
        if (isFirstMessage) 30.dp else 20.dp
    } else 20.dp
    val topStart = if (isSender) 0.dp else 20.dp
    val isExpanded = remember { mutableStateOf(false) }
    val isOverflowing = remember { mutableStateOf(false) }
    Surface(
        color = if (isSender) MaterialTheme.colorScheme.inversePrimary.copy(0.5f)
        else MaterialTheme.colorScheme.primary.copy(0.5f),
        modifier = modifier
            .padding(8.dp)
            .border(
                width = 0.6.dp, color = MaterialTheme.colorScheme.onSurface.copy(0.7f),
                shape = RoundedCornerShape(
                    topStart = topStart,
                    topEnd = topEnd,
                    bottomEnd = bottomEnd,
                    bottomStart = bottomStart
                )
            )
            .clip(
                RoundedCornerShape(
                    topStart = topStart,
                    topEnd = topEnd,
                    bottomEnd = bottomEnd,
                    bottomStart = bottomStart
                )
            )
            .widthIn(0.dp, 300.dp)
            .clickable { }
    ) {
        Column(
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .padding(top = 8.dp, start = 12.dp, end = 12.dp, bottom = 2.dp),
                maxLines = if (isExpanded.value) Int.MAX_VALUE else 28,
                overflow = TextOverflow.Ellipsis,
                onTextLayout = {
                    isOverflowing.value = it.hasVisualOverflow
                },
            )
            if (isOverflowing.value) {
                Text(
                    text = if (isExpanded.value) "Show Less" else "Show More",
                    modifier = Modifier
                        .padding(start = 12.dp, end = 12.dp, bottom = 8.dp)
                        .clickable {
                            isExpanded.value = !isExpanded.value
                        },
                    color = Color.Blue,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                )
            }
            Text(
                text = time,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Normal,
                fontSize = 8.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 16.dp, bottom = 2.dp),
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatBody(
    user: User,
    chatMessages: SnapshotStateList<ChatMessage>,
    recorderPlayer: AudioRecorderPlayer,
    modifier: Modifier = Modifier,
    showSheet: Boolean,
    onDismissShowSheet: () -> Unit,
    onSendAudio: (String, Int) -> Unit,
    showImagePickerSheet: Boolean,
    onDismissImagePicker: () -> Unit,
    onImageSelected: (Uri) -> Unit,
    fullScreenImageUri: Uri?,
    onImageClick: (Uri) -> Unit,
    onDismissFullScreenImage: () -> Unit
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var playingId by remember { mutableStateOf<String?>(null) }
    var playbackProgress by remember { mutableStateOf(0f) }

    LaunchedEffect(chatMessages.size) {
        coroutineScope.launch {
            if (chatMessages.isNotEmpty()) {
                listState.animateScrollToItem(chatMessages.lastIndex)
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(PureWhite),
            state = listState,
            reverseLayout = false,
            verticalArrangement = Arrangement.Bottom,
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            itemsIndexed(chatMessages) { index, message ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                ) {
                    if (index == 0 || index == 5) {
                        DateStamp(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }

                    when {
                        message.audioPath != null -> {
                            VoiceMessageBubble(
                                user = user,
                                message = message,
                                onPlay = {
                                    playingId = message.id
                                    recorderPlayer.playRecording(
                                        it,
                                        onProgress = { playbackProgress = it },
                                        onComplete = {
                                            playingId = null
                                            playbackProgress = 0f
                                        }
                                    )
                                },
                                onStop = {
                                    recorderPlayer.stopPlaying()
                                    playingId = null
                                    playbackProgress = 0f
                                },
                                isPlaying = (playingId == message.id),
                                currentProgress = if (playingId == message.id) playbackProgress else 0f
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }

                        !message.text.isNullOrEmpty() && message.imageUri != null -> {
                            TextImageMessageBubble(
                                message = message,
                                onImageClick = { onImageClick(it) }
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }

                        !message.text.isNullOrEmpty() -> {
                            TextMessageBubble(message = message)
                            Spacer(modifier = Modifier.height(10.dp))
                        }

                        message.imageUri != null -> {
                            ImageMessageBubble(
                                message = message,
                                onImageClick = { onImageClick(it) }
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }
        }


        if (showSheet) {
            val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            ModalBottomSheet(
                onDismissRequest = onDismissShowSheet,
                sheetState = sheetState,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                containerColor = Color.White,
            ) {
                BtmSheet(
                    recorder = recorderPlayer,
                    onSendAudio = onSendAudio,
                    onCancel = onDismissShowSheet,
                    onSendHide = onDismissShowSheet
                )
            }
        }


        if (showImagePickerSheet) {
            val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            ModalBottomSheet(
                onDismissRequest = onDismissImagePicker,
                sheetState = sheetState,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                containerColor = Color.White,
            ) {
                btnSheet(
                    onImageSelected = {
                        onImageSelected(it)
                        onDismissImagePicker()
                    }
                )
            }
        }

    }
}


@Composable
fun TextMessageBubble(message: ChatMessage) {
    val background = if (message.isSender) Purple1 else Color(0xFFECECEC)
    val textColor = if (message.isSender) Color.White else Color.White

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = if (message.isSender) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = if (message.isSender) 40.dp else 20.dp,
                        topEnd = if (message.isSender) 20.dp else 40.dp,
                        bottomEnd = if (message.isSender) 0.dp else 40.dp,
                        bottomStart = if (message.isSender) 40.dp else 0.dp
                    )
                )
                .background(background)
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(message.text ?: "", color = textColor)
        }
    }
}


data class AttachmentButton(
    val icon: ImageVector,
    val onClick: () -> Unit,
    val contentDescription: String? = null,
    val background: Brush,
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
@Preview(showBackground = true)
fun AttachmentsListButtons(
    buttons: List<AttachmentButton> = AttachmentButtons,
) {
    Surface(
        Modifier
            .fillMaxWidth()
            .verticalScroll(
                rememberScrollState()
            )
    ) {

        FlowRow {
            buttons.forEach { button ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
                ) {
                    IconButton(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(button.background),
                        onClick = { button.onClick() },
                        colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.onSurface),
                    ) {
                        Icon(
                            imageVector = button.icon,
                            contentDescription = button.contentDescription
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = button.contentDescription!!,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }
}

val AttachmentButtons: List<AttachmentButton> = listOf(
    AttachmentButton(
        Icons.Filled.Build,
        onClick = { },
        background = Brush.verticalGradient(
            listOf(
                Color(50, 70, 168, 100),
                Color(50, 70, 168)
            )
        ),
        contentDescription = "File"
    ),
    AttachmentButton(
        Icons.Filled.Face,
        onClick = { },
        background = Brush.verticalGradient(
            listOf(
                Color(224, 29, 101, 100),
                Color(224, 29, 101)
            )
        ),
        contentDescription = "Gallery"
    ),
    AttachmentButton(
        Icons.Filled.Star,
        onClick = { },
        background = Brush.verticalGradient(
            listOf(
                Color(255, 159, 28, 100),
                Color(255, 159, 28)
            )
        ),
        contentDescription = "Quick Reply"
    ),
    AttachmentButton(
        Icons.Filled.Place,
        onClick = { },
        background = Brush.verticalGradient(
            listOf(
                Color(0, 200, 83, 100),
                Color(0, 200, 83)
            )
        ),
        contentDescription = "Location"
    ),
    AttachmentButton(
        Icons.Filled.Info,
        onClick = { },
        background = Brush.verticalGradient(
            listOf(
                Color(224, 88, 29, 100),
                Color(224, 88, 29)
            )
        ),
        contentDescription = "Audio"
    ),
    AttachmentButton(
        Icons.Filled.Menu,
        onClick = { },
        background = Brush.verticalGradient(
            listOf(
                Color(119, 0, 255, 100),
                Color(119, 0, 255, 255)
            )
        ),
        contentDescription = "Catalogue"
    ),
    AttachmentButton(
        Icons.Filled.Person,
        onClick = { },
        background = Brush.verticalGradient(
            listOf(
                Color(29, 110, 224, 100),
                Color(29, 110, 224)
            )
        ),
        contentDescription = "Contact"
    ),
    AttachmentButton(
        Icons.Filled.Build,
        onClick = { },
        background = Brush.verticalGradient(
            listOf(
                Color(76, 175, 80, 100),
                Color(76, 175, 80)
            )
        ),
        contentDescription = "Poll"
    ),
)