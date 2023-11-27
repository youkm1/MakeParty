package com.example.after_party

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.after_party.databinding.ActivityMyPageBinding

class MyPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
