package app.chat.baat_chit.view.chatactivity.chatcomponents

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.chat.baat_chit.R
import app.chat.baat_chit.data.model.User
import app.chat.baat_chit.ui.theme.Purple1
import app.chat.baat_chit.ui.theme.Purple80
import coil.compose.rememberAsyncImagePainter
import java.util.UUID




data class ChatMessage(
    val id: String = UUID.randomUUID().toString(),
    val isSender: Boolean,
    val text: String? = null,
    val imageUri: Uri? = null,
    val audioPath: String? = null,
    val duration: Int = 0,
    val timestamp: Long = System.currentTimeMillis()
)

@Composable
fun VoiceMessageBubble(
    user: User,
    message: ChatMessage,
    onPlay: (String) -> Unit,
    onStop: () -> Unit,
    isPlaying: Boolean,
    currentProgress: Float
) {
    val bubbleColor = Purple80
    val progressColor = Purple1
    val timeTextColor = Color.Gray


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isSender) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(40.dp))
                .widthIn(max = 250.dp)
                .background(bubbleColor)
                .padding(vertical = 10.dp, horizontal = 10.dp)
            ,
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.wrapContentWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                ) {
                    Icon(
                        painter = rememberAsyncImagePainter(model = user.img),
                        tint = Color.Unspecified,
                        contentDescription = "profile",
                        modifier = Modifier
                            .size(50.dp)
                            .clickable { }
                    )
                }
                Spacer(Modifier.width(5.dp))
                IconButton(onClick = {
                    if (isPlaying) onStop()
                    else message.audioPath?.let { onPlay(it) }
                }) {
                    Icon(
                        painter = if (isPlaying) painterResource(id = R.drawable.play) else
                            painterResource(id = R.drawable.stop),
                        tint = Color.Unspecified,
                        contentDescription = null
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color(0xFF885A9E))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(currentProgress)
                            .background(progressColor)
                    )
                }

                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "0:${message.duration.toString().padStart(2, '0')}",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = timeTextColor,
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
            }
        }
    }
}

@Composable
fun ImageMessageBubble(message: ChatMessage,onImageClick: (Uri) -> Unit) {
    val bubbleColor = if (message.isSender) Purple1 else Color.White

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = if (message.isSender) Arrangement.End else Arrangement.Start
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = bubbleColor),
            modifier = Modifier.clickable { message.imageUri?.let { onImageClick(it) } }
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = message.imageUri),
                contentDescription = "Sent Image",
                modifier = Modifier
                    .padding(6.dp)
                    .sizeIn(maxWidth = 200.dp, maxHeight = 300.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun TextImageMessageBubble(message: ChatMessage, onImageClick: (Uri) -> Unit) {

    val bubbleColor = if (message.isSender) Purple80 else Color.White

    Row(
        modifier = Modifier
            .fillMaxWidth()
           .padding(horizontal = 16.dp)
        ,
        horizontalArrangement = if (message.isSender) Arrangement.End else Arrangement.Start
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = bubbleColor),
            modifier = Modifier.clickable { message.imageUri?.let { onImageClick(it) } }

        ) {
            message.imageUri?.let { uri ->
                Image(
                    painter = rememberAsyncImagePainter(model = message.imageUri),
                    contentDescription = "Sent Image",
                    modifier = Modifier
                        .padding(6.dp)
                        .sizeIn(maxWidth = 200.dp, maxHeight = 300.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { onImageClick(uri) },
                    contentScale = ContentScale.Crop
                )
            }
            message.text?.let {
                if (it.isNotBlank()) {
                    Text(
                        text = it,
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}







