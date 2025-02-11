package com.example.demo_autocapture

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var documentAutoCaptureStartButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews(view)
        setupDocumentAutoCaptureStartButton()
    }

    private fun setViews(view: View) {
        documentAutoCaptureStartButton = view.findViewById(R.id.document_auto_capture_start)
    }

    private fun setupDocumentAutoCaptureStartButton() {
        documentAutoCaptureStartButton.setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_BasicDocumentAutoCaptureFragment)
        }
    }
}
