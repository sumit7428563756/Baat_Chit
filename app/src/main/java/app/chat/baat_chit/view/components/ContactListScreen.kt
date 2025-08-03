package app.chat.baat_chit.view.components

import Contact
import android.Manifest
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import app.chat.baat_chit.R
import app.chat.baat_chit.data.model.User
import app.chat.baat_chit.navigation.Screens
import app.chat.baat_chit.ui.theme.PureWhite
import app.chat.baat_chit.ui.theme.Purple1
import app.chat.baat_chit.ui.theme.Purple80
import coil.compose.rememberAsyncImagePainter
import getContacts

@Composable
fun ContactListScreen() {
    val context = LocalContext.current
    var contacts by remember { mutableStateOf(listOf<Contact>()) }
    var allContacts by remember { mutableStateOf(listOf<Contact>()) }
    var selectedContact by remember { mutableStateOf<Contact?>(null) }
    var text by remember { mutableStateOf("") }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            val fetchedContacts = getContacts(context)
            allContacts = fetchedContacts
            contacts = fetchedContacts
        } else {
            Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.READ_CONTACTS)
    }


    LaunchedEffect(allContacts) {
        contacts = allContacts
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(680.dp)
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "New Chat",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Purple1
            )
            Spacer(modifier = Modifier.height(10.dp))
            Searchbar2(text = text) { query ->
                text = query
                contacts = if (query.isBlank()) {
                    allContacts
                } else {
                    allContacts.filter {
                        it.name.contains(query, ignoreCase = true) ||
                                it.number.contains(query)
                    }
                }
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(contacts) { contact ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .clickable { selectedContact = contact }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            MakeImage(name = contact.name, photoUri = contact.photoUri ?: "")
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = contact.name,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Purple1
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(
                                    if (selectedContact == contact) Purple1 else Color.Transparent,
                                    CircleShape
                                )
                                .border(
                                    1.dp, if (selectedContact == contact) Purple1 else Color.Black,
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (selectedContact == contact) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Selected",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(16.dp)
                                )
                            }
                        }
                    }
                }
                Divider(color = Color.Black, thickness = 2.dp)
            }
        }
    }
}

@Composable
fun ContactListCallScreen() {
    val context = LocalContext.current
    var contacts by remember { mutableStateOf(listOf<Contact>()) }
    var allContacts by remember { mutableStateOf(listOf<Contact>()) }
    var selectedContact by remember { mutableStateOf<Contact?>(null) }
    var text by remember { mutableStateOf("") }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            val fetchedContacts = getContacts(context)
            allContacts = fetchedContacts
            contacts = fetchedContacts
        } else {
            Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.READ_CONTACTS)
    }

    LaunchedEffect(allContacts) {
        contacts = allContacts
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(680.dp)
            .background(Color.White)
    ) {
        // Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "New Call",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Purple1
            )
            Spacer(modifier = Modifier.height(10.dp))
            Searchbar2(text = text) { query ->
                text = query
                contacts = if (query.isBlank()) {
                    allContacts
                } else {
                    allContacts.filter {
                        it.name.contains(query, ignoreCase = true) ||
                                it.number.contains(query)
                    }
                }
            }
        }


        selectedContact?.let { contact ->
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .height(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Purple80),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MakeImageCut(name = contact.name, photoUri = contact.photoUri ?: ""){
                            selectedContact = null
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = contact.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Purple1
                        )
                    }

                    Row {
                        BoxIcon2(
                            icon = painterResource(id = R.drawable.video),
                            tint = Color.White
                        ) {
//                            navController.navigate("${Screens.VideoCall.route}/${user.uid}")
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        BoxIcon2(
                            icon = painterResource(id = R.drawable.calls),
                            tint = Color.White
                        ) {
//                            navController.navigate("${Screens.AudioCall.route}/${user.uid}")
                        }
                    }
                }
            }
        }


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(contacts) { contact ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .clickable { selectedContact = contact }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            MakeImage(name = contact.name, photoUri = contact.photoUri ?: "")
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = contact.name,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Purple1
                            )
                        }

                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(
                                    if (selectedContact == contact) Purple1 else Color.Transparent,
                                    CircleShape
                                )
                                .border(
                                    1.dp,
                                    if (selectedContact == contact) Purple1 else Color.Black,
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (selectedContact == contact) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Selected",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
                Divider(color = Color.Black, thickness = 2.dp)
            }
        }
    }
}


@SuppressLint("RememberReturnType")
@Composable
fun MakeImage(name: String, photoUri: String) {
    val initial = name.firstOrNull()?.uppercase() ?: ""
    val backgroundColor = remember(name) {
        Color(
            red = (name.hashCode() shr 16 and 0xFF),
            green = (name.hashCode() shr 8 and 0xFF),
            blue = (name.hashCode() and 0xFF)
        )
    }

    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        if (photoUri.isNotBlank()) {
            Image(
                painter = rememberAsyncImagePainter(photoUri),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Text(
                text = initial,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@SuppressLint("RememberReturnType")
@Composable
fun MakeImageCut(name: String, photoUri: String, onClick: () -> Unit) {
    val initial = name.firstOrNull()?.uppercase() ?: ""
    val backgroundColor = remember(name) {
        Color(
            red = (name.hashCode() shr 16 and 0xFF),
            green = (name.hashCode() shr 8 and 0xFF),
            blue = (name.hashCode() and 0xFF)
        )
    }

    Box(
        modifier = Modifier
            .wrapContentSize()
    ) {

        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            if (photoUri.isNotBlank()) {
                Image(
                    painter = rememberAsyncImagePainter(photoUri),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Text(
                    text = initial,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }


        Box(
            modifier = Modifier
                .size(20.dp)
                .offset(x = 35.dp, y = (-8).dp) // float half-outside
                .clip(CircleShape)
                .background(Purple1)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Remove",
                tint = Color.White,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}



