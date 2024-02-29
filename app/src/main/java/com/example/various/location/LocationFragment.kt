package com.example.various.location

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.various.databinding.FragmentLocationBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

class LocationFragment : Fragment() {

    companion object {
        fun newInstance() = LocationFragment()
        const val TAG = "LocationFragment"
    }

    private lateinit var viewModel: LocationViewModel
    private lateinit var binding: FragmentLocationBinding

    /** com.google.android.gms:play-services-locationが必要 */
    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    /**
     * PRIORITY_HIGH_ACCURACYを使う
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Register the permissions callback, which handles the user's response to the
        // system permissions dialog. Save the return value, an instance of
        // ActivityResultLauncher. You can use either a val, as shown in this snippet,
        // or a lateinit var in your onAttach() or onCreate() method.
        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                    Log.d(TAG, "Permission is granted")
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // feature requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            }

        /* ACCESS_FINE_LOCATIONだけでいいっぽいな */
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.
                // 権限が付与されている場合

            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected, and what
                // features are disabled if it's declined. In this UI, include a
                // "cancel" or "no thanks" button that lets the user continue
                // using your app without granting the permission.

                // showInContextUI(...)

                // 一度拒否されてから、またアプリを立ち上げると、ここに入るっぽい
                Log.d(TAG, "shouldShowRequestPermissionRationale: true")

            }
            else -> {
                // You can directly ask for the permission.
                // The registered ActivityResultCallback gets the result of this request.
                requestPermissionLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                // Got last known location. In some rare situations this can be null.

                location.let {
                    Log.d(TAG, "latitude: ${it?.latitude}, longitude: ${it?.longitude}")
                }
            }

        /* addOnCompleteListenerは失敗と成功を両方受信できるリスナー */
        fusedLocationClient.lastLocation.addOnCompleteListener { task ->
            val location: Location? = task.result
            location.let {
                Log.d(TAG, "last latitude: ${it?.latitude}, longitude: ${it?.longitude}")
            }
        }

        fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            CancellationTokenSource().token
        ).addOnCompleteListener { task ->
            val location: Location? = task.result
            Log.d(TAG, "current location: $location")
        }

        binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }


}