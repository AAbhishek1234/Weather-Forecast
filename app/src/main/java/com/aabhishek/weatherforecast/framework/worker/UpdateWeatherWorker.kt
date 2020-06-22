package com.aabhishek.weatherforecast.framework.worker

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.aabhishek.weatherforecast.business.domain.usecases.UpdateWeatherForecast
import com.google.android.gms.location.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateWeatherWorker
constructor(
    val appContext: Context,
    val workerParams: WorkerParameters,
    val updateWeatherForecast: UpdateWeatherForecast
): CoroutineWorker(appContext, workerParams) {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private val TAG = UpdateWeatherWorker::class.java.simpleName

    private fun checkWifiStatus(): Boolean {
        val wifiMgr = appContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        if (wifiMgr != null) {
            if (wifiMgr.isWifiEnabled) {
                val wifiInfo = wifiMgr.connectionInfo
                return wifiInfo.networkId != -1
            } else {
                Log.i(TAG,"wifi not enabled")
                return false
            }
        } else {
            Log.i(TAG,"WIFI MGR IS NULL")
            return false
        }
    }

    override suspend fun doWork(): Result {
        var result: Result = Result.success()
        if (!checkWifiStatus()) {
            return Result.retry()
        }
        try {
            Log.i(TAG, "doing request")
            fusedLocationProviderClient = FusedLocationProviderClient(appContext)
            var locationRequest = LocationRequest()
            locationRequest.apply {
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                fastestInterval = 2000
                interval = 4000
            }
            locationCallback = object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult?) {
                    Log.i(TAG,"on location result : ")
                    if (p0 == null) {
                        Log.i(TAG, "location null")
                        result = Result.retry()
                    } else {
                        val lat = p0.lastLocation.latitude
                        val long = p0.lastLocation.longitude
                        Log.i(TAG,"pO not null latitude : $lat , long $long")
                        CoroutineScope(IO).launch {
                            Log.i(TAG,"calling updateWeatherForecast")
                            updateWeatherForecast(lat.toString(), long.toString())
                            result = Result.success()
                            fusedLocationProviderClient.removeLocationUpdates(locationCallback)
                        }
                    }
                }
            }
            if (ActivityCompat.checkSelfPermission(
                    appContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    appContext,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationProviderClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    appContext.mainLooper
                )
            } else {
                Log.i(TAG, "Permission not granted")
                Result.retry()
            }
        } catch (e: Exception) {
            return Result.retry()
        } finally {
            return Result.success()
        }
    }
}