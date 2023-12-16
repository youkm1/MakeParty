package com.example.after_party

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.after_party.databinding.ActivityGpsactivityBinding
import com.google.android.gms.location.LocationServices
import java.io.IOException
import java.util.*

class GPSActivity : AppCompatActivity() {
    val binding = ActivityGpsactivityBinding.inflate(layoutInflater)

    override fun onStart() {
        super.onStart()
        RequestPermissionsUtil(this).requestLocation() // 위치 권한 요청
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val locationText: TextView = binding.locationText
        val locationButton: Button = binding.locationButton
        locationButton.setOnClickListener {
            getLocation(locationText)
        }
        //locationText sharedRefeneces로 저장
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("address",locationText.text.toString())
        startActivity(intent)

        Log.d("as","${locationText.text}")
    }


    @SuppressLint("MissingPermission")
    private fun getLocation(textView: TextView) {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { success: Location? ->
                success?.let { location ->
                    val address = getAddress(location.latitude, location.longitude)?.get(0)
                    textView.text =
                        address?.let {
                            "${it.adminArea} ${it.locality} ${it.thoroughfare}"
                        }
                }
            }
            .addOnFailureListener { fail ->
                textView.text = fail.localizedMessage
            }
    }

    @Suppress("DEPRECATION")
    private fun getAddress(lat: Double, lng: Double): List<Address>? {
        lateinit var address: List<Address>

        return try {
            val geocoder = Geocoder(this, Locale.KOREA)
            address = geocoder.getFromLocation(lat, lng, 1) as List<Address>
            address
        } catch (e: IOException) {
            Toast.makeText(this, "주소를 가져 올 수 없습니다", Toast.LENGTH_SHORT).show()
            null
        }
    }

}