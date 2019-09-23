

package com.example.android.devbyteviewer.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.android.devbyteviewer.database.getDatabase
import com.example.android.devbyteviewer.repository.DictionariesRepository
import retrofit2.HttpException


class RefreshDataWorker(appContext: Context, params: WorkerParameters):
        CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    /**
     * A coroutine-friendly method to do your work.
     */
    override suspend fun doWork(): Payload {
        val database = getDatabase(applicationContext)
        val repository = DictionariesRepository(database)
        return try {
            repository.refreshDictionareis()
            Payload(Result.SUCCESS)
        } catch (e: HttpException) {
            Payload(Result.RETRY)
        }
    }
}