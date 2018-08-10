package com.xmoba.data.internal.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.xmoba.data.persistence.database.XMobaDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by david on 8/8/18.
 */
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): XMobaDatabase = Room.databaseBuilder(context, XMobaDatabase::class.java, "XMobaDatabase")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideUserDao(database: XMobaDatabase) = database.userDao()
}