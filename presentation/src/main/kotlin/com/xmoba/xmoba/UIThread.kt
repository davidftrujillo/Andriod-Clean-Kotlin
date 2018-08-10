package com.xmoba.xmoba

import com.xmoba.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by david on 6/8/18.
 */
@Singleton
class UIThread @Inject constructor(): PostExecutionThread {

    override fun getScheduler(): Scheduler {

        return AndroidSchedulers.mainThread()
    }
}
