package app.chat.baat_chit.view.Settings

import android.Manifest
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import app.chat.baat_chit.R
import app.chat.baat_chit.data.model.User
import app.chat.baat_chit.data.model.sample
import app.chat.baat_chit.ui.theme.Purple1
import app.chat.baat_chit.ui.theme.Purple80
import app.chat.baat_chit.view.chat.Img
import app.chat.baat_chit.view.components.BoxIcon
import app.chat.baat_chit.view.components.ContactListScreen
import app.chat.baat_chit.view.components.createImageUri
import app.chat.baat_chit.view.components.saveImageToGallery
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavController, user: User) {
    val state = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var fullScreenImageUri by remember { mutableStateOf<Any?>(null) }
    var show by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val imageModel: Any? = imageUri ?: user.img


    val context = LocalContext.current

    var capturedImageUri by remember { mutableStateOf<Uri?>(null) }

    val cropImageLauncher = rememberLauncherForActivityResult(
        contract = CropImageContract()
    ) { result ->
        if (result.isSuccessful) {
            imageUri = result.uriContent
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



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Purple1)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Settings",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(100.dp))
            }
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .border(
                        6.dp, Color.White,
                        CircleShape
                    )
                    .clickable {
                        imageModel?.let { fullScreenImageUri = it }
                    }
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = imageModel),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            TextButton(onClick = { show = true }) {
                Text(
                    text = "Edit",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            EditText(user = user)
            Spacer(modifier = Modifier.height(20.dp))
            EditText2(user = user)
            Spacer(modifier = Modifier.height(20.dp))
            EditText3(user = user)
            Spacer(modifier = Modifier.height(40.dp))
            TextButton(onClick = { }) {
                Text(
                    text = "Delete Account",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    IconRectangleBox(icon = painterResource(id = R.drawable.gellery)) {
                        show = false
                        launcherGallery.launch("image/*")
                    }
                    IconRectangleBox(icon = painterResource(id = R.drawable.camera)) {
                        show = false
                        launcherCameraPermission.launch(Manifest.permission.CAMERA)
                    }
                }
            }
        }

        fullScreenImageUri?.let { img ->
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
                        model = img,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

    }

}


@Composable
fun EditText(user: User) {
    var text by remember { mutableStateOf(user.name.toString()) }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
        },
        placeholder = {
            Text(
                user.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        },
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
        shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Black
        ),
        minLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .focusRequester(focusRequester)
    )
}


@Composable
fun EditText2(user: User) {
    var text by remember { mutableStateOf(user.about.toString()) }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.about),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
        },
        placeholder = {
            Text(
                user.about,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        },
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
        shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Black
        ),
        minLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .focusRequester(focusRequester)
    )
}


@Composable
fun EditText3(user: User) {
    var text by remember { mutableStateOf(user.phone.toString()) }
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        value = text,
        onValueChange = { },
        enabled = false,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.calls),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(20.dp)
            )
        },
        placeholder = {
            Text(
                user.phone,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )
        },
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
        shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Black
        ),
        minLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .focusRequester(focusRequester)
    )
}

@Composable
fun IconRectangleBox(icon: Painter, onClick: () -> Unit) {
    Box(modifier = Modifier
        .width(100.dp)
        .height(80.dp)
        .background(Purple1, RoundedCornerShape(15.dp))
        .clickable { onClick() }
        .clip(RoundedCornerShape(15.dp)), contentAlignment = Alignment.Center) {
        Icon(
            painter = icon, tint = Color.White, contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
    }
}