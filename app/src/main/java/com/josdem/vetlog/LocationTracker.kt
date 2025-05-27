package com.josdem.vetlog

import android.Manifest
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner


class LocationTracker(private val context: Context) : LocationListener, DefaultLifecycleObserver {

    private lateinit var locationManager: LocationManager

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    override fun onCreate(owner: LifecycleOwner) {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0f, this)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        locationManager.removeUpdates(this)
    }

    override fun onLocationChanged(location: Location) {
        val latitude = location.latitude
        val longitude = location.longitude
        Log.d("geolocation: ", "$latitude , $longitude")
    }
}
