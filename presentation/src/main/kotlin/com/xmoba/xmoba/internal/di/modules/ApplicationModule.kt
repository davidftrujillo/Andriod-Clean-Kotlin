package com.xmoba.xmoba.internal.di.modules

import android.app.Application
import android.content.Context
import com.xmoba.data.executor.JobExecutor
import com.xmoba.domain.executor.PostExecutionThread
import com.xmoba.domain.executor.ThreadExecutor
import com.xmoba.xmoba.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by david on 6/8/18.
 *
 * Dagger module that provides object which will live during the application lifecycle
 */
@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Application {
        return this.application
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return this.application.applicationContext
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }
}