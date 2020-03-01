package com.eosr14.kakaoimagesearch.common.analytics

import android.content.Context
import android.os.Bundle
import androidx.work.Worker
import androidx.work.WorkerParameters

const val KEY_EVENT = "key_event"

class JPAnalyticsWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val event = inputData.getString(KEY_EVENT)
        val parameter = Bundle().apply {
            for (key in inputData.keyValueMap.keys) {
                this.putString(key, inputData.getString(key))
            }
        }
        // TODO : 사내 로그 서버로 전송
//        sendServerLog(event, parameter)
        return Result.success()
    }
}