package com.example.demo_autocapture.image

import android.graphics.Bitmap
import com.gemalto.jp2.JP2Decoder
import com.innovatrics.dot.image.Image
import com.innovatrics.dot.image.ImageFormat

fun Image.createBitmap(): Bitmap {
    return when (this.format) {
        ImageFormat.JPEG_2000 -> com.example.demo_autocapture.image.createBitmapFromJpeg2000(this.bytes)
        else -> com.example.demo_autocapture.image.createBitmap(this.bytes)
    }
}

private fun createBitmapFromJpeg2000(bytes: ByteArray): Bitmap {
    val jp2Decoder = JP2Decoder(bytes)
    return jp2Decoder.decode()
}

private fun createBitmap(bytes: ByteArray): Bitmap {
    return android.graphics.BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
}
