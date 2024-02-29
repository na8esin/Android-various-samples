package com.example.various.location

import android.Manifest
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.various.R
import com.example.various.databinding.FragmentLocation2Binding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Tasks

/**
 * Tasks.awaitをviewmodelで使ってるパターン。
 * Extended ControlsでLocationの設定をするのを忘れずに！
 * ただ、設定しても時間がかかる気がする。その場合はgoogle mapで確認してみる
 */
class Location2Fragment : Fragment() {

    companion object {
        fun newInstance() = Location2Fragment()
        const val TAG = "Location2Fragment"
    }

    private lateinit var viewModel: Location2ViewModel
    private lateinit var binding: FragmentLocation2Binding
    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {

            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) -> {
                // 一度拒否されてから、またアプリを立ち上げると、ここに入るっぽい
                Log.d(LocationFragment.TAG, "shouldShowRequestPermissionRationale: true")

            }
            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }

        val task = fusedLocationClient.lastLocation
        // java.lang.IllegalStateException: Must not be called on the main application thread
        // val location = Tasks.await(task)
        // そりゃそうだ。

        // ということで、スレッドをブロックするような処理をviewmodelに移動する
        viewModel = ViewModelProvider(
            this, Location2ViewModelFactory(fusedLocationClient)
        )[Location2ViewModel::class.java]

        viewModel.lastLocation()

        binding = FragmentLocation2Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            this.viewmodel = viewModel

            // Specify the fragment view as the lifecycle owner of the binding.
            // This is used so that the binding can observe LiveData updates
            lifecycleOwner = viewLifecycleOwner
        }

        // Setup a click listener for the Submit and Skip buttons.
    }
}