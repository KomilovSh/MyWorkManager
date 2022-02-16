package com.example.myworkmanager.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.myworkmanager.ImageUtils
import java.lang.Exception

private const val LOG_TAG = "CleanFilesWorker"

class CleanFilesWorker(context: Context, workerParams: WorkerParameters) : Worker(
    context,
    workerParams
) {
    override fun doWork(): Result {
        return try {
            Thread.sleep(3000)
            Log.d(LOG_TAG, "Cleaning files!")

            ImageUtils.cleanFiles(applicationContext)

            Log.d(LOG_TAG, "Success!")
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}