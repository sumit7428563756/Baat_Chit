package app.chat.baat_chit.view.calls

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.animation.core.animateDp
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.chat.baat_chit.R
import app.chat.baat_chit.data.model.User
import app.chat.baat_chit.data.model.sampleList
import app.chat.baat_chit.ui.theme.Purple1
import app.chat.baat_chit.ui.theme.Purple80
import coil.compose.rememberAsyncImagePainter

@Composable
fun AudioCall(user: String = "0",navController: NavController) {
    val uid = sampleList.find { it.uid == user }!!
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Purple1)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
                .padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CallRow(user = uid, navController = navController )
            Spacer(modifier = Modifier.height(80.dp))
            Img2(user = uid)
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 50.dp)
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
           BoxCall(navController = navController)
        }
    }
}



@Composable
fun Img2(user: User) {
    val infiniteTransition = rememberInfiniteTransition(label = "")

    val scales = listOf(1f, 1.4f, 1.8f)
    val delays = listOf(0, 400, 800)

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(300.dp)
    ) {
        // Multi ripple waves
        delays.forEachIndexed { index, delay ->
            val scale by infiniteTransition.animateFloat(
                initialValue = 1f,
                targetValue = scales[index],
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1000,
                        delayMillis = delay,
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Restart
                ),
                label = "ripple$index"
            )

            val alpha by infiniteTransition.animateFloat(
                initialValue = 0.5f,
                targetValue = 0f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1000,
                        delayMillis = delay,
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Restart
                ),
                label = "alpha$index"
            )

            Box(
                modifier = Modifier
                    .size(150.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    }
                    .clip(CircleShape)
                    .background(Color.White)
            )
        }

        // Profile Image
        Image(
            painter = rememberAsyncImagePainter(model = user.img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape)
        )
    }
}

@Composable
fun CallRow(user: User,navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.back2),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(35.dp)
                .clickable { navController.popBackStack() }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text(
                    text = user.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Calling...",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}


@Composable
fun BoxCall(navController: NavController) {

    var micselect by remember { mutableStateOf(false) }
    var speakerselect by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                Color.White,
                RoundedCornerShape(24.dp)
            )
            .clip(RoundedCornerShape(24.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clickable { micselect = !micselect }
                    .clip(CircleShape)
                    .background(if (micselect) Purple1 else Purple80),
                contentAlignment = Alignment.Center
            ){
                Icon(painter = painterResource(id = R.drawable.mic2), tint = Color.White , contentDescription = null,
                    modifier = Modifier.size(25.dp))
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clickable { navController.popBackStack() }
                    .clip(CircleShape)
                    .background(Color.Red),
                contentAlignment = Alignment.Center
            ){
                Icon(painter = painterResource(id = R.drawable.callend), tint = Color.White , contentDescription = null,
                    modifier = Modifier.size(25.dp))
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clickable { speakerselect = !speakerselect }
                    .clip(CircleShape)
                    .background(if (speakerselect) Purple1 else Purple80),
                contentAlignment = Alignment.Center
            ){
                Icon(painter = painterResource(id = R.drawable.speaker), tint = Color.White , contentDescription = null,
                    modifier = Modifier.size(25.dp))
            }
        }
    }
}