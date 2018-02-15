package com.halim.hotelstajawal.domain.view

import com.halim.hotelstajawal.domain.exception.DomainException


interface View {

    // Data that will fill the view
    fun showLoadingDataProgress() {}

    fun hideLoadingDataProgress() {}

    fun showEmptyResult() {}

    fun hideEmptyResult() {}

    fun showMsg(msg: String) {}

    fun showErrorMsg(exception: DomainException) {}

    fun showRetry(exception: DomainException, onRetry: () -> Unit, onClose: () -> Unit) {}

    fun close() {}
}