package com.kuldip.presentation.util

import io.reactivex.rxjava3.core.Scheduler

interface SchedulerProvider {

    fun uiScheduler(): Scheduler

    fun backgroundScheduler(): Scheduler
}