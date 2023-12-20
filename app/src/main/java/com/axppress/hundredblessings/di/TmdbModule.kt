package com.axppress.hundredblessings.di

import com.axppress.hundredblessings.ui.fragment.mainfragment.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {
    viewModel { MainViewModel() }
}
