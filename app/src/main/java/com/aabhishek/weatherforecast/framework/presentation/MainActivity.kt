package com.aabhishek.weatherforecast.framework.presentation

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.location.LocationManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aabhishek.weatherforecast.R
import com.aabhishek.weatherforecast.business.domain.entity.WeatherInfo
import com.aabhishek.weatherforecast.databinding.ActivityMainBinding
import com.aabhishek.weatherforecast.framework.BaseApplication
import com.aabhishek.weatherforecast.framework.presentation.viewmodel.MainViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityMainBinding

    val viewModel: MainViewModel by viewModels{
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as BaseApplication).appComponent
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.weatherInfo = WeatherInfo()
        //Log.i(TAG,"viewModel initialized :  + $viewModel")
        setInitialViewState()
        checkLocationPermission()
        initializeObservers()
    }

    private fun setInitialViewState() {
        binding.progress.visibility = View.VISIBLE
    }

    private fun initializeObservers() {
        viewModel.updatedWeather.observe(this, Observer {
            Log.i(TAG, "weather data : $it")
            it?.let {
                binding.apply {
                    weatherInfo = it
                    temp.text = String.format("%.2f", it.temp - 273.15)
                    tempMin.text = String.format("%.2f", it.tempMin - 273.15)
                    tempMax.text = String.format("%.2f", it.tempMax - 273.15)
                    tempFL.text = String.format("%.2f", it.tempFeelsLike - 273.15)
                    executePendingBindings()
                    progress.visibility = View.GONE
                }
            }
        })
    }

    private fun checkLocationPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        var granted = false
        when (requestCode) {
            REQUEST_LOCATION -> {
                granted = grantResults[0] == PackageManager.PERMISSION_GRANTED
            }
        }

        if (granted) {
            checkIfGPSEnabled()
            viewModel.enqueueRefreshWeather()
        } else {
            finish()
        }
    }

    private fun checkIfGPSEnabled() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!LocationManagerCompat.isLocationEnabled(locationManager)) {
            turnOnGps()
        }
    }

    private fun turnOnGps() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage("Enable GPS").setCancelable(false)
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, which -> startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)) })
            .setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }


    companion object {
        const val REQUEST_LOCATION = 1;
    }
}