package com.example.demo_autocapture.documentautocapture

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.innovatrics.dot.camera.CameraFacing
import com.innovatrics.dot.camera.CameraPreviewScaleType
import com.innovatrics.dot.document.autocapture.DocumentAutoCaptureDetection
import com.innovatrics.dot.document.autocapture.DocumentAutoCaptureFragment
import com.innovatrics.dot.document.autocapture.DocumentAutoCaptureResult
import com.innovatrics.dot.document.autocapture.MrzValidation
import com.innovatrics.dot.document.autocapture.PlaceholderType
import com.innovatrics.dot.document.autocapture.QualityAttributeThresholds
import com.innovatrics.dot.document.autocapture.ValidationMode
import com.example.demo_autocapture.DotSdkViewModel
import com.example.demo_autocapture.DotSdkViewModelFactory
import com.example.demo_autocapture.MainViewModel
import com.example.demo_autocapture.ui.createGson
import kotlinx.coroutines.launch

class BasicDocumentAutoCaptureFragment : DocumentAutoCaptureFragment() {

    private val gson = createGson()
    private val mainViewModel: MainViewModel by activityViewModels()
    private val dotSdkViewModel: DotSdkViewModel by activityViewModels {
        DotSdkViewModelFactory(
            requireActivity().application
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDotSdkViewModel()
    }

    override fun provideConfiguration(): Configuration {
        return Configuration(
            cameraFacing = CameraFacing.BACK,
            cameraPreviewScaleType = CameraPreviewScaleType.FILL,
            validationMode = ValidationMode.STRICT,
            placeholderType = PlaceholderType.CORNERS_ONLY,
            isDetectionLayerVisible = true,
            mrzValidation = MrzValidation.NONE,
            qualityAttributeThresholds = QualityAttributeThresholds(
                minConfidence = 0.9,
                minSharpness = 0.6,
                maxHotspotsScore = 0.6
            )
        )
    }

    private fun setupDotSdkViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                dotSdkViewModel.state.collect { state ->
                    if (state.isInitialized) {
                        start()
                    }
                    state.errorMessage?.let {
                        Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
                        dotSdkViewModel.notifyErrorMessageShown()
                    }
                }
            }
        }
        dotSdkViewModel.initializeDotSdkIfNeeded()
    }

    override fun onNoCameraPermission() {
        mainViewModel.notifyNoCameraPermission()
    }

    override fun onCaptured(result: DocumentAutoCaptureResult) {
        println("Result image: ${result.bgraRawImage}")
        println("Result data: ${gson.toJson(result.document)}")
    }

    override fun onProcessed(detection: DocumentAutoCaptureDetection) {
    }
}
