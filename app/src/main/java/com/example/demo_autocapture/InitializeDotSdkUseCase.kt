package com.example.demo_autocapture

import android.content.Context
import android.content.res.Resources
import com.innovatrics.dot.core.DotSdk
import com.innovatrics.dot.core.DotSdkConfiguration
import com.innovatrics.dot.document.DotDocumentLibrary
import com.innovatrics.dot.face.DotFaceLibrary
import com.innovatrics.dot.face.DotFaceLibraryConfiguration
import com.innovatrics.dot.face.detection.fast.DotFaceDetectionFastModule
import com.innovatrics.dot.face.expressionneutral.DotFaceExpressionNeutralModule
import java.io.InputStream
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InitializeDotSdkUseCase(
    private val dotSdk: DotSdk = DotSdk,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {

    suspend operator fun invoke(context: Context) = withContext(dispatcher) {
        val configuration = createDotSdkConfiguration(context)
        dotSdk.initialize(configuration)
    }

    private fun createDotSdkConfiguration(context: Context) = DotSdkConfiguration(
        context = context,
        licenseBytes = readLicenseBytes(context.resources),
        libraries = listOf(
            DotDocumentLibrary(),
            createDotFaceLibrary(),
        ),
    )

    private fun readLicenseBytes(resources: Resources) = resources.openRawResource(R.raw.dot_license).use(InputStream::readBytes)

    private fun createDotFaceLibrary() = DotFaceLibrary(
        configuration = DotFaceLibraryConfiguration(
            modules = listOf(
                DotFaceDetectionFastModule.of(),
                DotFaceExpressionNeutralModule.of(),
            ),
        ),
    )
}
