package com.example.lesson15workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import java.util.concurrent.TimeUnit

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: ")
        val workRequest: WorkRequest =
            OneTimeWorkRequestBuilder<DemoWorker>()
                .setInitialDelay(5, TimeUnit.SECONDS)
                .build()

        WorkManager
            .getInstance(this)
            .enqueue(workRequest)
    }
}