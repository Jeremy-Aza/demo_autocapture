package com.example.demo_autocapture.io

import java.io.File

interface ResourceCopier {

    fun copyToFile(resourceId: Int, destinationFile: File)
}
