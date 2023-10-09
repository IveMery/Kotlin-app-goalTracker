package com.example.goaltracker1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.goaltracker1.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // GENERAMOS UNA LOCALIZACION
        val hotel = LatLng(-30.073334664263975, -71.37744672112966)
        val playa = LatLng(-30.07353891851828, -71.37453921709013)
        val Totoralillo = LatLng(-30.07351562514591, -71.37464645031176)

        //AGREGAMOS UN MARCADOR EN EL MAPA
        mMap.addMarker(MarkerOptions().position(playa).title("hotel"))
        mMap.addMarker(MarkerOptions().position(Totoralillo).title("Totoralillo"))
        //POSICIONAMOS LA CAMARA EN LA LOCALIZACION
        // Mueve la cámara instantáneamente a hotel con un zoom de 15.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hotel,15f))
        // Construye una CameraPosition centrada en totoralillo y anima la cámara a esa posición.
        val cameraPosition = CameraPosition.Builder()
            .target(Totoralillo) // Establece el centro del mapa en totoralillo
            .zoom(17f) // Establece el zoom
            .bearing(90f) // Establece la orientación de la cámara al este
            .tilt(30f) // Establece la inclinación de la cámara a 30 grados
            .build() // Crea una CameraPosition a partir del constructor
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

    }
}