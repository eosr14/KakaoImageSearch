package com.eosr14.kakaoimagesearch.common.analytics

import android.content.Context
import android.os.Bundle
import androidx.work.Worker
import androidx.work.WorkerParameters

class JPAnalyticsWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val event = inputData.getString(KEY_EVENT) ?: ""
        val parameter = Bundle().apply {
            inputData.keyValueMap.keys.forEach { key ->
                if (key != KEY_EVENT) {
                    this.putString(key, inputData.getString(key))
                }
            }
        }
        sendServerLog(event, parameter)
        return Result.success()
    }

    private fun sendServerLog(event: String, parameter: Bundle) {
        // TODO : 사내 로그 서버로 전송
    }


}