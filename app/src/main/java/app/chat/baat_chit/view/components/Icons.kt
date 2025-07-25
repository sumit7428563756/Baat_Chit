package app.chat.baat_chit.view.components



import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun BoxIcon(icon : Painter,tint : Color, onClick : () -> Unit){

   Box(modifier = Modifier
       .size(40.dp)
       .clip(CircleShape),
       contentAlignment = Alignment.Center){
         Icon(painter = icon, tint = tint, contentDescription = null,
             modifier = Modifier.size(25.dp))
   }

}

