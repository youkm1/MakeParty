package com.example.after_party

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DetailReservation : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private val storeLat = 37.54276779 // 가게의 위도
    private val storeLng = 126.97374716 // 가게의 경도

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_reservation)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val storeLocation = LatLng(storeLat, storeLng)
        mMap.addMarker(MarkerOptions().position(storeLocation).title("가게 이름"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(storeLocation, 15f))
    }
}