package com.halim.hotelstajawal.ui.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.halim.hotelstajawal.di.scope.ActivityScope
import javax.inject.Inject

@ActivityScope
class ViewModelFactory<out VM : ViewModel?> @Inject constructor(private val viewModel: Lazy<VM>) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = viewModel.value as T
}