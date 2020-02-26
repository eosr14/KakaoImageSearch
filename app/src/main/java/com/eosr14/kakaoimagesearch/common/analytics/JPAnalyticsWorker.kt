package com.eosr14.kakaoimagesearch.common.analytics

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class JPAnalyticsWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {
    override fun doWork(): Result {
        // TODO : 사내 로그 서버로 전송

        workDataOf()
//        sendLogWithServer(event, params)
        return Result.success()
    }
}