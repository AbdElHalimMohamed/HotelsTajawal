package com.halim.hotelstajawal.ui.activity

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.FrameLayout
import com.halim.hotelstajawal.R
import com.halim.hotelstajawal.domain.exception.DomainException
import com.halim.hotelstajawal.domain.presenter.Presenter
import com.halim.hotelstajawal.domain.presenter.bus.Bus
import com.halim.hotelstajawal.domain.view.View
import com.halim.hotelstajawal.ui.util.ErrorHandler
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.widget_empty_screen.*
import kotlinx.android.synthetic.main.widget_error_view.*
import javax.inject.Inject


abstract class BaseActivity<P : Presenter<*>> : AppCompatActivity(), View {

    @Inject
    open lateinit var presenter: P
    @Inject
    lateinit var bus: Bus

    abstract fun getLayoutId(): Int
    abstract fun onViewCreated(presenter: P)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_base)

        AndroidInjection.inject(this)

        val layoutId = getLayoutId()

        if (layoutId > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                content.isTransitionGroup = true
            }
            content.addView(LayoutInflater.from(this).inflate(layoutId, null), FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT))
        }

        val toolbar = getToolbar()

        if (toolbar != null) {
            setSupportActionBar(toolbar)
            if (showHomeAsUp()) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setHomeButtonEnabled(true)
            }
        }

        onViewCreated(presenter)

        val title = getActivityTitle()
        if (title != null) {
            this.title = title
        }

        presenter.init()
    }

    override fun onStart() {
        super.onStart()
        presenter.resumeReceivingEvents()
    }

    override fun onStop() {
        presenter.pauseReceivingEvents()
        super.onStop()
    }

    open fun getEmptyViewMsgRes(): Int = R.string.empty_default

    open fun getActivityTitle(): String? = null

    open fun getToolbar(): Toolbar? = null

    open fun showHomeAsUp() = false

    override fun showLoadingDataProgress() {
        showLoader()
    }

    override fun hideLoadingDataProgress() {
        fullScreenLoader.visibility = INVISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem?) =
            when (item?.itemId) {
                android.R.id.home -> {
                    supportFinishAfterTransition()
                    true
                }
                else -> {
                    false
                }
            }

    override fun showEmptyResult() {
        showEmptyView()

        emptyMsg.text = getString(getEmptyViewMsgRes())
    }

    override fun showErrorMsg(exception: DomainException) {
        showErrorView()
        retryBtn.visibility = INVISIBLE

        errorMsg.text = ErrorHandler.getErrorMessage(this, exception)
    }

    override fun showRetry(exception: DomainException, onRetry: () -> Unit, onClose: () -> Unit) {
        showErrorMsg(exception)
        retryBtn.visibility = VISIBLE

        retryBtn.setOnClickListener {
            onRetry()
        }
    }

    private fun showErrorView() {
        errorView.visibility = VISIBLE
        emptyView.visibility = INVISIBLE
    }

    private fun showEmptyView() {
        errorView.visibility = INVISIBLE
        emptyView.visibility = VISIBLE
    }

    private fun showLoader() {
        fullScreenLoader.visibility = VISIBLE
        errorView.visibility = INVISIBLE
        emptyView.visibility = INVISIBLE
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}