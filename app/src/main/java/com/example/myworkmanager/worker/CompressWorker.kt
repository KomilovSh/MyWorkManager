package com.example.myworkmanager.worker

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.myworkmanager.ImageUtils
import java.lang.Exception

private const val LOG_TAG = "CompressWorker"
private const val KEY_IMAGE_PATH = "IMAGE_PATH"
private const val KEY_ZIP_PATH = "ZIP_PATH"

class CompressWorker(context: Context, workerParams: WorkerParameters) : Worker(
    context,
    workerParams
) {
    override fun doWork(): Result {
        try {
            Thread.sleep(3000)
            Log.d(LOG_TAG, "Compressing files!")

            val imagePaths = inputData.keyValueMap
                .filter { it.key.startsWith(KEY_IMAGE_PATH) }
                .map { it.value as String }

            val zipFile = ImageUtils.createZipFile(applicationContext, imagePaths.toTypedArray())
            val output = Data.Builder()
                .putString(KEY_ZIP_PATH, zipFile.path)
                .build()

            Log.d(LOG_TAG, "Success!")
            return Result.success(output)
        } catch (e: Exception) {
            Log.e(LOG_TAG, "Error executing work: " + e.message, e)
            return Result.failure()
        }
    }
}