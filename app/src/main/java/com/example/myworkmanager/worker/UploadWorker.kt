package com.example.myworkmanager.worker

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.myworkmanager.ImageUtils
import java.lang.Exception

private const val LOG_TAG = "UploadWorker"
private const val KEY_ZIP_PATH = "ZIP_PATH"

class UploadWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        return try {
            Thread.sleep(3000)
            Log.d(LOG_TAG, "Uploading file!")

            val zipPath = inputData.getString(KEY_ZIP_PATH)

            ImageUtils.uploadFile(Uri.parse(zipPath))

            Log.d(LOG_TAG, "Success!")
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}