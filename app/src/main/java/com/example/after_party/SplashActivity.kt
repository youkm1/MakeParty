package com.example.after_party

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
<<<<<<< HEAD

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize

=======
import android.support.annotation.ColorRes
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
>>>>>>> bd8d0b13384ce4f24686db4a2e181cf04788f26d
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
<<<<<<< HEAD
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.sp

=======
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import kotlinx.coroutines.coroutineScope
>>>>>>> bd8d0b13384ce4f24686db4a2e181cf04788f26d
import kotlinx.coroutines.delay


@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            After_PartyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SplashScreen()
<<<<<<< HEAD

                }
            }
        }

    }


    @Composable
    fun SplashScreen() {
=======
                }
            }
        }
    }

    @Preview
    @Composable
    private fun SplashScreen() {
>>>>>>> bd8d0b13384ce4f24686db4a2e181cf04788f26d
        LaunchedEffect(
            key1 = true
        ) {
            delay(2000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
<<<<<<< HEAD
            //val intent = Intent(this@SplashActivity, MainActivity::class.java)

=======
>>>>>>> bd8d0b13384ce4f24686db4a2e181cf04788f26d
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "splash-logo"
            )
            Text(
                text = "단 한 번의 파티",
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
<<<<<<< HEAD
            )
        }

=======
                )
        }
>>>>>>> bd8d0b13384ce4f24686db4a2e181cf04788f26d
    }

}

<<<<<<< HEAD

=======
>>>>>>> bd8d0b13384ce4f24686db4a2e181cf04788f26d
