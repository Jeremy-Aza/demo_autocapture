package com.example.demo_autocapture

data class MainState(
    val isProcessing: Boolean = false,
    val errorMessage: String? = null,
)

data class DotSdkState(
    val isInitialized: Boolean = false,
    val errorMessage: String? = null,
)