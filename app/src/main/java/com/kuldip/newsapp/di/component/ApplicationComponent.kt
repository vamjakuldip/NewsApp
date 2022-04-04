package com.kuldip.newsapp.di.component

import android.app.Application
import com.kuldip.newsapp.app.NewsApplication
import com.kuldip.newsapp.di.module.app.*
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApplicationModule::class, UiModule::class, DataModule::class, CacheModule::class, RemoteModule::class]
)
interface ApplicationComponent {

    fun inject(application: NewsApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent

    }

}