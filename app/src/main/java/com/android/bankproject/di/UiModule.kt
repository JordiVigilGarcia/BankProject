package com.android.bankproject.di

import com.android.bankproject.home_screen.vm.HomeViewModelActivity
import com.android.bankproject.transactions_screen.vm.HomeViewModel
import com.android.bankproject.profile_screen.vm.UserViewModel
import com.android.bankproject.utils.SharedTransactionVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { HomeViewModelActivity() }
    viewModel { HomeViewModel(get()) }
    viewModel { UserViewModel(get()) }
    viewModel { SharedTransactionVM() }
}