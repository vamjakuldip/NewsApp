package com.kuldip.newsapp.util

import com.kuldip.presentation.util.SchedulerProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A Class provides "SchedulerProvider" implementation for our application.
 */
@Singleton
class AppSchedulerProvider @Inject constructor() : SchedulerProvider {

    override fun uiScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun backgroundScheduler(): Scheduler {
        return Schedulers.io()
    }
}