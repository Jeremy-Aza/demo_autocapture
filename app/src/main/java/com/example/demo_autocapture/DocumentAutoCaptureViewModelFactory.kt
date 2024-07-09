package com.example.demo_autocapture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DocumentAutoCaptureViewModelFactory: ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DocumentAutoCaptureViewModel(CreateUiResultUseCase()) as T
    }
}