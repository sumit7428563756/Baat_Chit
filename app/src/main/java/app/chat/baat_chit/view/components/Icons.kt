package app.chat.baat_chit.view.components



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.chat.baat_chit.ui.theme.Purple80
import app.chat.baat_chit.ui.theme.gradientBrush

@Composable
fun BoxIcon(icon : Painter,tint : Color, onClick : () -> Unit){

   Box(modifier = Modifier
       .size(40.dp)
       .background(Purple80, CircleShape)
       .clickable { onClick() }
       .clip(CircleShape),
       contentAlignment = Alignment.Center){
         Icon(painter = icon, tint = tint, contentDescription = null,
             modifier = Modifier.size(25.dp))
   }

}

@Composable
fun ChatMessage(text : String){

    Box(modifier = Modifier
        .size(12.dp)
        .background(gradientBrush, CircleShape)
        .clip(CircleShape),
        contentAlignment = Alignment.Center){
       Text(text = text,
           fontWeight = FontWeight.SemiBold,
           fontSize = 10.sp,
           color = Color.White)
    }

}

