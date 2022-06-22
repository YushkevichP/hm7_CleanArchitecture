//package com.example.hm7_cleanarchitecture.googlemap
//
//import android.Manifest
//import android.annotation.SuppressLint
//
//import android.content.pm.PackageManager
//import android.location.Location
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.core.content.ContextCompat
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.lifecycleScope
//
//import com.example.hm7_cleanarchitecture.databinding.FragmentMapBinding
//import com.example.hm7_cleanarchitecture.viewmodels.ListViewModel
//import com.example.hm7_cleanarchitecture.viewmodels.MapViewModel
//import com.google.android.gms.location.LocationListener
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.GoogleMap
//import com.google.android.gms.maps.LocationSource
//import com.google.android.gms.maps.model.LatLng
//import com.google.android.gms.maps.model.MarkerOptions
//import kotlinx.coroutines.flow.launchIn
//import kotlinx.coroutines.flow.onEach
//import kotlinx.coroutines.launch
//import org.koin.android.ext.android.inject
//import org.koin.androidx.viewmodel.ext.android.viewModel
//
//
//class MapFragment : Fragment() {
//
//    private var _binding: FragmentMapBinding? = null
//    private val binding get() = requireNotNull(_binding)
//
//    private val viewModel by viewModel<MapViewModel>()
//
//
////    private var googleMap: GoogleMap? = null
////    private var locationListener: LocationSource.OnLocationChangedListener? = null
//
// //   private val locationService by inject<LocationService>()
//
//    @SuppressLint("MissingPermission")  // запрашиваем пермишн
//    private val permissionLauncher = registerForActivityResult(
//        ActivityResultContracts.RequestPermission()
//    ) { permissionGranted ->
//        if (permissionGranted) {
//            viewLifecycleOwner.lifecycleScope.launch {
//                locationService.getCurrentLocation()?.let(::moveCameraToLocation)
//            }
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View {
//        return FragmentMapBinding.inflate(inflater, container, false)
//            .also { _binding = it }
//            .root
//    }
//
//    @SuppressLint("MissingPermission")
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
//
//
//
//        locationService
//            .getLocationFlow()
//            .onEach {
//                //когда приходит локация, то мы ее отрисовваываем через лисенер
//                locationListener?.onLocationChanged(it)
//
//            }
//            .launchIn(viewLifecycleOwner.lifecycleScope)
//
//
//        //чтоб получить карту
//        binding.mapView.getMapAsync { map ->
//            googleMap = map.apply {
//                uiSettings.isCompassEnabled = true
//                uiSettings.isMyLocationButtonEnabled = true
//                uiSettings.isZoomControlsEnabled = true
//            }
//
//            map.isMyLocationEnabled = hasLocationPermission()
//
//            map.setLocationSource(object : LocationSource {
//                override fun activate(listener: LocationSource.OnLocationChangedListener) {
//                    locationListener = listener
//                }
//
//                override fun deactivate() {
//                    locationListener = null
//                }
//
//            })
//            map.addMarker(
//                MarkerOptions()
//                    .title("My marker")
//                    .position(
//                        LatLng(
//                            28.9165,
//                            48.4357
//                        )
//                    )
//            )
//
//        }
//
//        binding.mapView.onCreate(savedInstanceState)
//
//
//
//        ViewCompat.setOnApplyWindowInsetsListener(view) { _, insets ->
//            val systemBarInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            googleMap?.setPadding(
//                systemBarInsets.left,
//                systemBarInsets.top,
//                systemBarInsets.right,
//                systemBarInsets.bottom
//            )
//            WindowInsetsCompat.CONSUMED
//        }
//    }
//
//
//    override fun onStart() {
//        super.onStart()
//        binding.mapView.onStart()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        binding.mapView.onStop()
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        binding.mapView.onSaveInstanceState(outState)
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding.mapView.onDestroy()
//        googleMap = null
//        _binding = null
//    }
//
//    //анимация камеры
//    private fun moveCameraToLocation(location: Location) {
//        val current = LatLng(location.latitude, location.longitude)
//        googleMap?.animateCamera(
//            CameraUpdateFactory.newLatLngZoom(current, 17f)
//        )
//    }
//
//
//    private fun hasLocationPermission(): Boolean {
//        return ContextCompat.checkSelfPermission(
//            requireContext(),
//            Manifest.permission.ACCESS_FINE_LOCATION
//        ) == PackageManager.PERMISSION_GRANTED
//    }
//}
//
////
////       ViewCompat.setOnApplyWindowInsetsListener(binding.mapView){ _, insets->
////
////           googleMap?.setPadding(
////
////           )
////           WindowInsetsCompat.CONSUMED
////       }