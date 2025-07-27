package app.chat.baat_chit.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.chat.baat_chit.R
import app.chat.baat_chit.ui.theme.Purple1


data class BottomItems(
    val icon: Painter,
    val route: String,
    val label: String,
    val name: String
)


@Composable
fun BottomBar(): List<BottomItems> {
    return listOf(
        BottomItems(
            icon = painterResource(id = R.drawable.home),
            route = Screens.Home.route,
            label = Screens.Home.label,
            name = "Chat"
        ),
        BottomItems(
            icon = painterResource(id = R.drawable.calls),
            route = Screens.Calls.route,
            label = Screens.Calls.label,
            name = "Calls"
        ),
        BottomItems(
            icon = painterResource(id = R.drawable.settings),
            route = Screens.Settings.route,
            label = Screens.Settings.label,
            name = "Settings"
        )
    )
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<BottomItems>,
    selectedIndex: Int,
) {
    Surface(
        tonalElevation = 10.dp,
        shadowElevation = 4.dp,
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                val isSelected = index == selectedIndex

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = item.icon,
                        contentDescription = item.label,
                        tint = if (isSelected) Purple1 else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = item.name,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (isSelected) Purple1 else Color.Gray
                    )
                }
            }
        }
    }
}


