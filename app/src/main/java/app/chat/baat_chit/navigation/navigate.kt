package app.chat.baat_chit.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.chat.baat_chit.view.authentication.First
import app.chat.baat_chit.view.authentication.OtpScreen
import app.chat.baat_chit.view.authentication.Success

@Composable
fun Nav(){
    val navcontroller = rememberNavController()

    NavHost(navController = navcontroller, startDestination = Screens.FirstScreen.route) {
     composable(Screens.FirstScreen.route){
         First(navController = navcontroller)
     }
        composable("otpscreen_route/{number}") { backStackEntry ->
            val number = backStackEntry.arguments?.getString("number") ?: ""
            OtpScreen(number = number, navController = navcontroller)
        }
        composable(Screens.Success.route){
            Success(navController = navcontroller)
        }

    }

}




data class BottomNavItem(
    val icon: Painter,
    val selectedIcon: Painter,
    val route: String,
    val label: String
)

sealed class Screens(val route : String, val label : String) {
    object FirstScreen : Screens("firstscreen_route", "FirstScreen")
    object  OtpScreen : Screens("otpscreen_route","OtpScreen" )
    object  Success : Screens("success_route","Success" )
}




