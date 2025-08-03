package app.chat.baat_chit.view.chatactivity

import android.content.Context
import android.content.Intent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.chat.baat_chit.navigation.Screens
import app.chat.baat_chit.ui.theme.Baat_ChitTheme
import app.chat.baat_chit.view.calls.AudioCall
import app.chat.baat_chit.view.calls.VideoCall
import app.chat.baat_chit.view.chatactivity.chatscreens.Conversation

sealed class Screens(val route: String, label: String) {
    object Conversation : app.chat.baat_chit.view.chatactivity.Screens("conversation_screen" ,"Conversation")
    object AudioCall : app.chat.baat_chit.view.chatactivity.Screens("audiocall_screen", "AudioCall")
    object VideoCall : app.chat.baat_chit.view.chatactivity.Screens("videocall_screen" ,"VideoCall")
}



class ConversationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //WindowCompat.setDecorFitsSystemWindows(window, false)

        val userId = intent.getStringExtra("user_uid") ?: "1"

        setContent {
            Baat_ChitTheme{
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = app.chat.baat_chit.view.chatactivity.Screens.Conversation.route
                ) {
                    composable(app.chat.baat_chit.view.chatactivity.Screens.Conversation.route) {
                        Conversation(navController,userId)
                    }
                    composable("audiocall_screen/{id}", arguments = listOf(navArgument("id"){type = NavType.StringType})){
                        val id = it.arguments?.getString("id")
                        if( id != null){
                            AudioCall(id,navController)
                        }
                    }
                    composable("videocall_screen/{id}", arguments = listOf(navArgument("id"){type = NavType.StringType})){
                        val id = it.arguments?.getString("id")
                        if( id != null){
                            VideoCall(id,navController)
                        }
                    }
                }
            }

        }
    }
}


fun openConversation(context: Context, userId: String) {
    val intent = Intent(context, ConversationActivity::class.java).apply {
        putExtra("user_uid", userId)
    }
    context.startActivity(intent)
}



