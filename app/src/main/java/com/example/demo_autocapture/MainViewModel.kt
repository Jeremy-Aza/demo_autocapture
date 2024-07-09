package com.example.demo_autocapture

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    data class MainState(
        val isProcessing: Boolean = false,
        val errorMessage: String? = null
    )

    private val mutableState: MutableLiveData<MainState> = MutableLiveData<MainState>().apply { value = MainState() }
    val state: LiveData<MainState> = mutableState

    fun setProcessing(enabled: Boolean) {
        mutableState.value = state.value!!.copy(isProcessing = enabled)
    }

    fun notifyNoCameraPermission() {
        mutableState.value = state.value!!.copy(errorMessage = "No camera permission.")
    }

    fun notifyErrorMessageShown() {
        mutableState.value = state.value!!.copy(errorMessage = null)
    }
}