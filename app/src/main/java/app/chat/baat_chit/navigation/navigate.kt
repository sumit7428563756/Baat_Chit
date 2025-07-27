package app.chat.baat_chit.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.chat.baat_chit.data.model.User
import app.chat.baat_chit.ui.theme.Purple1
import app.chat.baat_chit.view.Settings.Settings
import app.chat.baat_chit.view.authentication.First
import app.chat.baat_chit.view.authentication.OtpScreen
import app.chat.baat_chit.view.authentication.Success
import app.chat.baat_chit.view.calls.Calls
import app.chat.baat_chit.view.chat.HomeScreen

@Composable
fun Nav(user: List<User>) {
    val navcontroller = rememberNavController()
    val items = BottomBar()
    val navBackStackEntry by navcontroller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var selectedIndex = items.indexOfFirst { it.route == currentRoute }.coerceAtLeast(0)

    val bottomBarRoutes = listOf(
        Screens.Home.route,
        Screens.Home.route,
        Screens.Calls.route,
        Screens.Settings.route,
    )

    val showBottomBar = currentRoute in bottomBarRoutes



    Scaffold(bottomBar = {
        if (showBottomBar) {
            BottomNavigationBar(
                navController = navcontroller,
                items = items,
                selectedIndex = selectedIndex
            )
        }
    }) { innerpadding ->
        NavHost(
            navController = navcontroller,
            startDestination = Screens.FirstScreen.route,
            modifier = Modifier.padding(innerpadding)
        ) {
            composable(Screens.FirstScreen.route) {
                First(navController = navcontroller)
            }
            composable("otpscreen_route/{number}") { backStackEntry ->
                val number = backStackEntry.arguments?.getString("number") ?: ""
                OtpScreen(number = number, navController = navcontroller)
            }
            composable(Screens.Success.route) {
                Success(navController = navcontroller)
            }
            composable(Screens.Home.route) {
                HomeScreen(navController = navcontroller, user = user.first())
            }
            composable(Screens.Calls.route) {
                Calls(navController = navcontroller)
            }
            composable(Screens.Settings.route) {
                Settings(navController = navcontroller)
            }

        }
    }

}


data class BottomNavItem(
    val icon: Painter,
    val selectedIcon: Painter,
    val route: String,
    val label: String
)

sealed class Screens(val route: String, val label: String) {
    object FirstScreen : Screens("firstscreen_route", "FirstScreen")
    object OtpScreen : Screens("otpscreen_route", "OtpScreen")
    object Success : Screens("success_route", "Success")
    object Home : Screens("home_route", "Home")
    object Calls : Screens("calls_route", "Calls")
    object Settings : Screens("settings_route", "Settings")
}




