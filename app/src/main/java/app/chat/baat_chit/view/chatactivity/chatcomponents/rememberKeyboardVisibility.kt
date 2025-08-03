package app.chat.baat_chit.view.chatactivity.chatcomponents


import android.graphics.Rect
import android.net.Uri
import android.util.Log
import android.view.ViewTreeObserver
import androidx.compose.animation.EnterExitState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.chat.baat_chit.R
import app.chat.baat_chit.ui.theme.Purple1
import app.chat.baat_chit.ui.theme.Purple80
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun rememberKeyboardVisibility(): Boolean {
    val view = LocalView.current
    var isKeyboardVisible by remember { mutableStateOf(false) }

    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val r = Rect()
            view.getWindowVisibleDisplayFrame(r)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - r.bottom
            isKeyboardVisible = keypadHeight > screenHeight * 0.15
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

    return isKeyboardVisible
}


@Composable
fun Chat(
    imageUri: Uri? = null,
    onAttachClick: () -> Unit = {},
    onSendClicked: (ChatMessage) -> Unit = {},
    onMicClicked: () -> Unit = {},
    onImageSelected: (Uri) -> Unit = {},
) {
    var text by remember { mutableStateOf("") }
    val imeVisible = rememberKeyboardVisibility()
    val keyboardController = LocalSoftwareKeyboardController.current
    val hasImage = imageUri != null && imageUri != Uri.EMPTY
    val shape = if (imeVisible) RectangleShape else RoundedCornerShape(40.dp)
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val focusRequester = remember { FocusRequester() }
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val focusManager = LocalFocusManager.current


    LaunchedEffect(imeVisible) {
        Log.d("Keyboard", "Keyboard is ${if (imeVisible) "Visible" else "Hidden"}")
    }


    val message = ChatMessage(
        isSender = true,
        text = text.takeIf { it.isNotBlank() },
        imageUri = imageUri.takeIf { it != null && it != Uri.EMPTY }
    )


    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Column {
                if (hasImage) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(imageUri),
                            contentDescription = "Selected Image",
                            modifier = Modifier
                                .height(120.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .border(1.dp, Color.Gray, RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.cross),
                            contentDescription = "Remove Image",
                            tint = Color.White,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(10.dp)
                                .background(Color.Black.copy(alpha = 0.6f), CircleShape)
                                .size(20.dp)
                                .clickable { onImageSelected(Uri.EMPTY) }
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.attach),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(35.dp)
                            .padding(end = 8.dp)
                            .clickable { onAttachClick() }
                    )

                    OutlinedTextField(
                        value = text,
                        onValueChange = {
                            text = it
                            scope.launch {
                                listState.animateScrollToItem(listState.layoutInfo.totalItemsCount)
                            }
                        },
                        placeholder = { Text("Type your message...", color = Purple1) },
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
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Purple1
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .requiredHeightIn(min = 50.dp, max = 150.dp)
                            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                            .background(Color.White)
                            .bringIntoViewRequester(bringIntoViewRequester)
                            .onFocusChanged { focusState ->
                                if (focusState.isFocused) {
                                    coroutineScope.launch {
                                        delay(200)
                                        bringIntoViewRequester.bringIntoView()
                                    }
                                }
                            }
                            .focusRequester(focusRequester),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))


                    if (text.isNotBlank() || hasImage) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(Purple80)
                                .clickable {
                                    onSendClicked(message)
                                    text = ""
                                    onImageSelected(Uri.EMPTY)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.send),
                                contentDescription = "Send",
                                tint = Purple1,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.mic__1_),
                            contentDescription = "Voice",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(35.dp)
                                .padding(start = 4.dp)
                                .clickable { onMicClicked() }
                        )
                    }
                }

            }
        }
    }
}





