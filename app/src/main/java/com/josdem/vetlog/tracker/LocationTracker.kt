/*
  Copyright 2025 Jose Morales contact@josdem.io

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
*/

package com.josdem.vetlog.tracker

import android.Manifest
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.josdem.vetlog.BuildConfig
import com.josdem.vetlog.R
import com.josdem.vetlog.helper.ConnectivityHelper
import com.josdem.vetlog.service.RetrofitHelper
import com.josdem.vetlog.service.VetlogService
import com.josdem.vetlog.util.ContextUtils
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class LocationTracker(
    private val context: Context,
) : LocationListener,
    DefaultLifecycleObserver {
    private lateinit var locationManager: LocationManager
    private lateinit var vetlogService: VetlogService
    private lateinit var contextUtils: ContextUtils
    private lateinit var connectivityHelper: ConnectivityHelper
    private lateinit var token: String

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    override fun onCreate(owner: LifecycleOwner) {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0f, this)
        vetlogService = RetrofitHelper.getInstance().create(VetlogService::class.java)
        contextUtils = ContextUtils(context)
        connectivityHelper = ConnectivityHelper(contextUtils)
        token = BuildConfig.TOKEN
    }

    override fun onDestroy(owner: LifecycleOwner) {
        locationManager.removeUpdates(this)
    }

    override fun onLocationChanged(location: Location) {
        val latitude = location.latitude
        val longitude = location.longitude
        Log.d("geolocation: ", "$latitude , $longitude")
        if (!connectivityHelper.isMobileConnected()) {
            Toast.makeText(context, R.string.network_message, Toast.LENGTH_SHORT).show()
            return
        }
        MainScope().launch {
            val result = vetlogService.sendLocation(token, latitude, longitude)
            Log.d("response: ", result.body().toString())
        }
    }
}
