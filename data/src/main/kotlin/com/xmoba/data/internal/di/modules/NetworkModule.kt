package com.xmoba.data.internal.di.modules

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by david on 7/8/18.
 */
@Module
class NetworkModule {

    private val API_BASE_URL = "https://api.randomuser.me/?seed=xmoba"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        val httpClientBuilder = OkHttpClient.Builder()

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClientBuilder.addInterceptor(httpLoggingInterceptor)

        httpClientBuilder.addInterceptor { chain ->
            var request = chain!!.request()
            val url = request.url().newBuilder().addQueryParameter("seed", "xmoba").build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)!!
        }

        return httpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }
}