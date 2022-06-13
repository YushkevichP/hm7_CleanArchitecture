package com.example.hm7_cleanarchitecture.koin

import com.example.hm7_cleanarchitecture.viewmodels.ListViewModel
import com.example.hm7_cleanarchitecture.viewmodels.PersonDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        ListViewModel(get())
    }

        // так передаем параметр какой-то, если нужно
    viewModel {
            (id: Int) -> PersonDetailsViewModel(id, get()) }

}