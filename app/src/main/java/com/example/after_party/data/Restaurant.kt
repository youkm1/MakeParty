package com.example.after_party.data

import android.net.Uri
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

data class Restaurant(
    val name:String,
    val address:String,

)

