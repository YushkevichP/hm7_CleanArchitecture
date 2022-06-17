package com.example.hm7_cleanarchitecture.koin

import com.example.hm7_cleanarchitecture.viewmodels.ListViewModel
import com.example.hm7_cleanarchitecture.viewmodels.PersonDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::ListViewModel)
    viewModelOf(::PersonDetailsViewModel)
}
