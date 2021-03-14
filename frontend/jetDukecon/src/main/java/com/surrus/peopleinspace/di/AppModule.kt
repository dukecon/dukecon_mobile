package com.surrus.peopleinspace.di

import org.dukecon.android.ui.PeopleInSpaceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { PeopleInSpaceViewModel(get()) }
}
