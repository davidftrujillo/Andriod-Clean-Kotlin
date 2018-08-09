package com.xmoba.data.internal.di.modules

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by david on 9/8/18.
 */
@Module
class SharedPreferencesModule(val context: Context) {

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences {

        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}