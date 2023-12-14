package com.example.after_party

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.after_party.databinding.ActivityGpsactivityBinding


class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityGpsactivityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*binding.composeRoot.setContent {
            MainScreen()
        }*/
    }

}