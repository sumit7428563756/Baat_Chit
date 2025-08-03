package app.chat.baat_chit.view.chatactivity.chatscreens


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import app.chat.baat_chit.R
import app.chat.baat_chit.data.model.User
import app.chat.baat_chit.data.model.sampleList
import app.chat.baat_chit.navigation.Screens
import app.chat.baat_chit.ui.theme.PureWhite
import app.chat.baat_chit.ui.theme.Purple1
import app.chat.baat_chit.view.calls.CallColumn
import app.chat.baat_chit.view.chatactivity.chatcomponents.AudioRecorderPlayer
import app.chat.baat_chit.view.chatactivity.chatcomponents.AudioRecorderUI
import app.chat.baat_chit.view.chatactivity.chatcomponents.Chat
import app.chat.baat_chit.view.chatactivity.chatcomponents.ChatBody
import app.chat.baat_chit.view.chatactivity.chatcomponents.ChatMessage
import app.chat.baat_chit.view.components.BoxIcon
import app.chat.baat_chit.view.components.createImageUri
import app.chat.baat_chit.view.home.Img
import coil.compose.AsyncImage
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Conversation(
    navHostController: NavHostController = rememberNavController(),
    userid: String = "0"
) {
    val uid = sampleList.find { it.uid == userid }!!
    val topBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topBarState)
    val chatMessages = remember { mutableStateListOf<ChatMessage>() }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val coroutineScope = rememberCoroutineScope()
    var showSheet by remember { mutableStateOf(false) }
    var show by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val state = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val recorderPlayer = remember { AudioRecorderPlayer() }
    val State = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showImagePickerSheet by remember { mutableStateOf(false) }
    var fullScreenImageUri by remember { mutableStateOf<Uri?>(null) }

    Scaffold(
        bottomBar = {
            Chat(
                onSendClicked = { message ->
                    if (message.text != null || message.imageUri != null) {
                        chatMessages.add(message)
                        selectedImageUri = null

                        coroutineScope.launch {
                            listState.animateScrollToItem(chatMessages.lastIndex)
                        }
                    }
                },
                onMicClicked = {
                    coroutineScope.launch {
                        showSheet = true
                    }
                },
                onAttachClick = {
                    coroutineScope.launch {
                        show = true
                    }
                },
                imageUri = selectedImageUri,
                onImageSelected = { uri -> selectedImageUri = uri }
            )
        },
        containerColor = Purple1
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Purple1)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp)
            ) {
                ChatRow(user = uid, navHostController)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(PureWhite)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 20.dp)
                ) {
                    ChatBody(
                        modifier = Modifier
                            .padding(innerPadding)
                            .imePadding(),
                        user = uid,
                        chatMessages = chatMessages,
                        recorderPlayer = recorderPlayer,
                        showSheet = showSheet,
                        onDismissShowSheet = { showSheet = false },
                        onSendAudio = { path, duration ->
                            chatMessages.add(
                                ChatMessage(
                                    id = System.currentTimeMillis().toString(),
                                    audioPath = path,
                                    duration = duration,
                                    isSender = true
                                )
                            )
                        },
                        showImagePickerSheet = showImagePickerSheet,
                        onDismissImagePicker = { showImagePickerSheet = false },
                        onImageSelected = { uri ->
                            chatMessages.add(
                                ChatMessage(
                                    id = System.currentTimeMillis().toString(),
                                    imageUri = uri,
                                    isSender = true
                                )
                            )
                        },
                        fullScreenImageUri = fullScreenImageUri,
                        onImageClick = { fullScreenImageUri = it },
                        onDismissFullScreenImage = { fullScreenImageUri = null }
                    )
                    if (showSheet) {
                        ModalBottomSheet(
                            onDismissRequest = { showSheet = false },
                            sheetState = State,
                            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                            containerColor = Color.White,
                        ) {
                            BtmSheet(
                                recorder = recorderPlayer,
                                onSendAudio = { path, duration ->
                                    if (path.isNotEmpty()) {
                                        chatMessages.add(
                                            ChatMessage(
                                                isSender = true,
                                                audioPath = path,
                                                duration = duration
                                            )
                                        )
                                    }
                                },
                                onCancel = { showSheet = false },
                                onSendHide = { showSheet = false }
                            )
                        }
                    }
                    if (show) {
                        ModalBottomSheet(
                            onDismissRequest = { show = false },
                            sheetState = state,
                            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                            containerColor = Color.White,
                        ) {
                            btnSheet(
                                onImageSelected = { uri ->
                                    selectedImageUri = uri
                                    show = false
                                }
                            )
                        }
                    }

                    if (fullScreenImageUri != null) {
                        Dialog(
                            onDismissRequest = { fullScreenImageUri = null },
                            properties = DialogProperties(usePlatformDefaultWidth = false)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black)
                                    .clickable { fullScreenImageUri = null },
                                contentAlignment = Alignment.Center
                            ) {
                                AsyncImage(
                                    model = fullScreenImageUri,
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("ContextCastToActivity")
@Composable
fun ChatRow(user: User, navHostController: NavHostController) {
    val activity = (LocalContext.current as? Activity)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 🔹 Left side: Back + Image + Column
        Row(verticalAlignment = Alignment.CenterVertically) {
            BoxIcon(
                icon = painterResource(id = R.drawable.back2),
                tint = Color.White,
                onClick = { activity?.finish() }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Img(user = user) {}

            Spacer(modifier = Modifier.width(4.dp))

            Column {
                Text(
                    text = user.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Active Now 🟢",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }


        Row(verticalAlignment = Alignment.CenterVertically) {
            BoxIcon(
                icon = painterResource(id = R.drawable.video),
                tint = Color.White,
                onClick = { navHostController.navigate("${app.chat.baat_chit.view.chatactivity.Screens.VideoCall.route}/${user.uid}") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            BoxIcon(
                icon = painterResource(id = R.drawable.calls),
                tint = Color.White,
                onClick = { navHostController.navigate("${app.chat.baat_chit.view.chatactivity.Screens.AudioCall.route}/${user.uid}") }
            )
        }
    }
}


@OptIn(ExperimentalPermissionsApi::class, ExperimentalPermissionsApi::class)
@Composable
fun BtmSheet(
    recorder: AudioRecorderPlayer,
    onSendAudio: (String, Int) -> Unit,
    onCancel: () -> Unit,
    onSendHide: () -> Unit
) {
    val permissionState = rememberPermissionState(Manifest.permission.RECORD_AUDIO)
    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {

        if (permissionState.status.isGranted) {
            AudioRecorderUI(
                recorder = recorder,
                onSendAudio = onSendAudio,
                startRecordingImmediately = true,
                onCancel = onCancel,
                onSendHide = onSendHide
            )
        } else {
            Text("Microphone permission is required")
        }
    }
}


@Composable
fun btnSheet(
    onImageSelected: (Uri) -> Unit,
) {
    val context = LocalContext.current
    var capturedImageUri by remember { mutableStateOf<Uri?>(null) }

    val cropImageLauncher = rememberLauncherForActivityResult(
        contract = CropImageContract()
    ) { result ->
        if (result.isSuccessful) {
            result.uriContent?.let { onImageSelected(it) }
        } else {
            Toast.makeText(context, "Image cropping failed", Toast.LENGTH_SHORT).show()
        }
    }

    val launcherCamera = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && capturedImageUri != null) {
            cropImageLauncher.launch(
                CropImageContractOptions(
                    uri = capturedImageUri!!,
                    CropImageOptions().apply {
                        fixAspectRatio = true
                        aspectRatioX = 1
                        aspectRatioY = 1
                        cropMenuCropButtonTitle = "Done"
                        showCropOverlay = true
                    }
                )
            )
        }
    }


    val launcherCameraPermission = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            val newuri = createImageUri(context)
            capturedImageUri = newuri
            launcherCamera.launch(newuri)
        } else {
            Toast.makeText(context, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    val launcherGallery = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            cropImageLauncher.launch(
                CropImageContractOptions(
                    uri = it,
                    CropImageOptions().apply {
                        fixAspectRatio = true
                        aspectRatioX = 1
                        aspectRatioY = 1
                        cropMenuCropButtonTitle = "Done"
                        showCropOverlay = true
                    }
                )
            )
        }
    }

    Column(
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                BoxIcons2(
                    icon = painterResource(id = R.drawable.gellery),
                    tint = Color.White,
                    onClick = { launcherGallery.launch("image/*") })
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Photos", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            }
            Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                BoxIcons2(
                    icon = painterResource(id = R.drawable.gps),
                    tint = Color.White,
                    onClick = {})
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Location", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            }
            Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                BoxIcons2(
                    icon = painterResource(id = R.drawable.camera),
                    tint = Color.White,
                    onClick = { launcherCameraPermission.launch(Manifest.permission.CAMERA) })
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Camera", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                BoxIcons2(
                    icon = painterResource(id = R.drawable.contact),
                    tint = Color.White,
                    onClick = {})
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Contacts", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            }
            Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                BoxIcons2(
                    icon = painterResource(id = R.drawable.document),
                    tint = Color.White,
                    onClick = {})
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Document", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            }
            Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
                BoxIcons2(
                    icon = painterResource(id = R.drawable.calender),
                    tint = Color.White,
                    onClick = {})
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Event", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}


@Composable
fun BoxIcons2(icon: Painter, tint: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .size(50.dp)
            .clip(CircleShape)
            .background(Purple1),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(35.dp)
        )
    }
}


