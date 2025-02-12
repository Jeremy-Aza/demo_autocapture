package com.example.demo_autocapture

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    companion object {
        const val OVERLAY_ALPHA_NORMAL_SCREEN = 0.0f
        const val OVERLAY_ALPHA_DIM_SCREEN = 0.6f
    }

    private val mutableState: MutableLiveData<MainState> =
        MutableLiveData<MainState>().apply { value = MainState() }
    private val state: LiveData<MainState> = mutableState

    private lateinit var overlayView: View
    private lateinit var progressIndicator: CircularProgressIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViews()
        setupMainViewModel()
    }

    private fun notifyErrorMessageShown() {
        mutableState.value = state.value!!.copy(errorMessage = null)
    }

    private fun setViews() {
        overlayView = findViewById(R.id.overlay)
        progressIndicator = findViewById(R.id.progress_indicator)
    }

    private fun setupMainViewModel() {
        state.observe(this) { state ->
            when (state.isProcessing) {
                true -> dimScreen()
                false -> restoreScreen()
            }
            state.errorMessage?.let {
                Snackbar.make(findViewById(android.R.id.content), it, Snackbar.LENGTH_SHORT).show()
                notifyErrorMessageShown()
            }
        }
    }

    private fun dimScreen() {
        overlayView.alpha = OVERLAY_ALPHA_DIM_SCREEN
        progressIndicator.show()
    }

    private fun restoreScreen() {
        overlayView.alpha = OVERLAY_ALPHA_NORMAL_SCREEN
        progressIndicator.hide()
    }
}
