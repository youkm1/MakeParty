package com.example.after_party

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.after_party.data.User
import com.example.after_party.databinding.ActivityMyPageBinding


class MyPageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            After_PartyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    myPage(context=applicationContext)
                    bottomApp()


                }
            }
        }

    }

}

@Composable
fun myPage(context: Context) {
    val user = User() //data에 있는 내가 만든 데이터클래스(User) 사용한 것
    AndroidView(factory = {
        View.inflate(it, R.layout.activity_my_page, null)

    },
        modifier = Modifier.fillMaxSize(),
        update = {
            val logoutBtn = it.findViewById<Button>(R.id.logout_btn)
            logoutBtn.setOnClickListener {
                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
            }
            val WelcomeTxt = it.findViewById<TextView>(R.id.WelcomeText)

            WelcomeTxt.text = "${user.name}님 안녕하세요"
        })


}