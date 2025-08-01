package app.chat.baat_chit.view.account

import android.Manifest
import android.net.Uri
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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import app.chat.baat_chit.R
import app.chat.baat_chit.data.model.User
import app.chat.baat_chit.navigation.Screens
import app.chat.baat_chit.ui.theme.Purple1
import app.chat.baat_chit.view.Settings.IconRectangleBox
import app.chat.baat_chit.view.components.LoginButton
import app.chat.baat_chit.view.components.createImageUri
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadPhoto(user: User,navController: NavController) {

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
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Upload Your Profile Image",
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Purple1
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .border(
                        6.dp, Purple1,
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
                    text = "Add Image",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Purple1
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 10.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginButton(name = "Next") {
                navController.navigate(Screens.Success.route)
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