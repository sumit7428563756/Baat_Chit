package app.chat.baat_chit.view.chatactivity.chatcomponents

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import app.chat.baat_chit.R
import app.chat.baat_chit.ui.theme.Purple
import app.chat.baat_chit.ui.theme.Purple1
import app.chat.baat_chit.ui.theme.Purple80
import kotlinx.coroutines.delay
import kotlin.random.Random
import androidx.compose.ui.platform.LocalContext as LocalContext1


@Composable
fun AudioRecorderUI(
    recorder: AudioRecorderPlayer,
    context: Context = LocalContext1.current,
    onSendAudio: (String, Int) -> Unit,
    startRecordingImmediately: Boolean = false,
    onCancel: () -> Unit,
    onSendHide: () -> Unit
) {
    var isRecording by remember { mutableStateOf(false) }

    val waveHeights =
        remember { List(25) { androidx.compose.animation.core.Animatable(Random.nextFloat()) } }

    if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO)
        != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            101
        )
    }

    // Auto-start recording
    LaunchedEffect(startRecordingImmediately) {
        if (startRecordingImmediately && !isRecording) {
            recorder.startRecording(context)
            isRecording = true
        }
    }

    // Amplitude animation
    LaunchedEffect(isRecording) {
        while (isRecording) {
            val amplitude = recorder.getAmplitude().coerceIn(0, 32767)
            val threshold = 2000

            waveHeights.forEach {
                it.animateTo(
                    targetValue = if (amplitude > threshold) Random.nextFloat() else 0.1f,
                    animationSpec = tween(durationMillis = 120)
                )
            }

            delay(120)
        }
    }

    // UI layout
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (isRecording) {
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        waveHeights.forEach { anim ->
                            Box(
                                modifier = Modifier
                                    .width(4.dp)
                                    .height((20 * anim.value + 10).dp)
                                    .padding(horizontal = 1.dp)
                                    .clip(RoundedCornerShape(50))
                                    .background(Purple1)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))


            Box(modifier = Modifier.fillMaxWidth()) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Purple80, RoundedCornerShape(30.dp))
                        .clip(RoundedCornerShape(30.dp))
                        .padding(horizontal = 10.dp)
                        .align(Alignment.Center)
                ) {
                    TextButton(onClick = {
                        if (isRecording) {
                            recorder.stopRecording()
                            isRecording = false
                            onCancel()
                        }
                    }) {
                        Text(
                            text = "Cancel",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Purple1
                        )
                    }

                    Spacer(modifier = Modifier.width(50.dp))

                    TextButton(onClick = {
                        if (isRecording) {
                            recorder.stopRecording()
                            isRecording = false
                        }
                        val path = recorder.getRecordingFilePath()
                        val duration = recorder.getDurationInSec()
                        onSendAudio(path, duration)
                        onSendHide()
                    }) {
                        Text(
                            text = "Send",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Purple1
                        )
                    }
                }



                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .align(Alignment.Center)
                        .clip(CircleShape)
                        .background(Purple1),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.mic__1_),
                        contentDescription = "Mic",
                        tint = Color.White,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }


    }
}






