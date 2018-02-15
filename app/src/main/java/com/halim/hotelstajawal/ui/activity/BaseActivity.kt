package com.halim.hotelstajawal.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.halim.hotelstajawal.domain.presenter.Presenter
import com.halim.hotelstajawal.domain.view.View
import dagger.android.AndroidInjection
import javax.inject.Inject


abstract class BaseActivity<P : Presenter<View>> : AppCompatActivity(), View {

    @Inject
    open lateinit var presenter: P

    abstract fun getLayoutId(): Int
    abstract fun onViewCreated(presenter: P)

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())

        onViewCreated(presenter)
    }
}