package com.halim.hotelstajawal.ui.viewmodel

import android.arch.lifecycle.ViewModel
import com.halim.hotelstajawal.domain.presenter.Presenter
import com.halim.hotelstajawal.domain.view.View

// ViewModel is used to retain the Presenter classes from Domain during configuration changes
abstract class BaseViewModel<out P : Presenter<*>>(val presenter: P) : ViewModel()