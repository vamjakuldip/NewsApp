package com.kuldip.newsapp.di.module.app

import com.kuldip.newsapp.util.AppSchedulerProvider
import com.kuldip.presentation.util.SchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class UiModule {

    @Binds
    abstract fun bindSchedulerProvider(appSchedulerProvider: AppSchedulerProvider): SchedulerProvider

}