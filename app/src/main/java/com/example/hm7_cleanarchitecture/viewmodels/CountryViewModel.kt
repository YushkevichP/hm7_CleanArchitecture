package com.example.hm7_cleanarchitecture.viewmodels

import androidx.lifecycle.ViewModel
import com.example.hm7_cleanarchitecture.data.service.LocationService
import com.example.hm7_cleanarchitecture.domain.usecase.GetCountryUseCase

class CountryViewModel(
    private val locationService: LocationService,
    private val getCountryUseCase: GetCountryUseCase,
) : ViewModel() {

}