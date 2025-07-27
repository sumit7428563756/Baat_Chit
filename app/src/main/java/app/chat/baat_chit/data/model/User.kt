package app.chat.baat_chit.data.model

import android.annotation.SuppressLint
import app.chat.baat_chit.R
import java.util.Date
import java.util.Locale


data class User(
    val uid: String,
    val name: String,
    val img: Int,
    val phone: String,
    val time : String,
    val lastmsg : String,
    val about : String
)


@SuppressLint("ConstantLocale")
val sdf = java.text.SimpleDateFormat("hh:mm ", Locale.getDefault())
val currentTime = sdf.format(Date())

val sample = listOf(
    User(
        uid = "0",
        name = "Sumit Kumar Chaurasiya",
        img = R.drawable.man1,
        phone = "1234567890",
        time =  currentTime,
        lastmsg = "I love chatting",
        about = "Hey there! i am using BaatChit"
    ),
)


val sampleList = listOf(
    User(
        uid = "1",
        name = "Rinku",
        img = R.drawable.man2,
        phone = "1234567890",
        time =  currentTime,
        lastmsg = "I love eating",
        about = "Hey there! i am using BaatChit"
    ),
    User(
        uid = "2",
        name = "Pinku",
        img = R.drawable.man3,
        phone = "1234567890",
        time =  currentTime,
        lastmsg = "I love dancing",
        about = "Hey there! i am using BaatChit"
    ),
    User(
        uid = "3",
        name = "Minku",
        img = R.drawable.man4,
        time =  currentTime,
        phone = "1234567890",
        lastmsg = "I love driving",
        about = "Hey there! i am using BaatChit"
    ),
    User(
        uid = "4",
        name = "Rinki",
        img = R.drawable.girl,
        phone = "1234567890",
        time =  currentTime,
        lastmsg = "I love Singing",
        about = "Hey there! i am using BaatChit"
    ),
    User(
        uid = "5",
        name = "Sintu",
        img = R.drawable.man5,
        phone = "1234567890",
        time =  currentTime,
        lastmsg = "I love Walking",
        about = "Hey there! i am using BaatChit"
    ), User(
        uid = "6",
        name = "Sittu",
        img = R.drawable.man6,
        phone = "1234567890",
        time =  currentTime,
        lastmsg = "I love Reading",
        about = "Hey there! i am using BaatChit"
    ), User(
        uid = "7",
        name = "Ankit",
        img = R.drawable.man7,
        phone = "1234567890",
        time =  currentTime,
        lastmsg = "I love Travelling",
        about = "Hey there! i am using BaatChit"
    ), User(
        uid = "8",
        name = "Anik",
        img = R.drawable.man8,
        phone = "1234567890",
        time =  currentTime,
        lastmsg = "I love BaatChit",
        about = "Hey there! i am using BaatChit"

    ), User(
        uid = "9",
        name = "Piyush",
        img = R.drawable.man9,
        phone = "1234567890",
        time =  currentTime,
        lastmsg = "I love this app",
        about = "Hey there! i am using BaatChit"
    ),
)