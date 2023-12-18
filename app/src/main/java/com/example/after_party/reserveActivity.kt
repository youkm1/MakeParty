package com.example.after_party

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class reserveActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve)
        val reservationConfirmed: Button = findViewById(R.id.reservation_confirmed)

        reservationConfirmed.setOnClickListener {
            val intent = Intent(this, ReservationConfirmActivity::class.java)
            startActivity(intent)
        }
    }
}