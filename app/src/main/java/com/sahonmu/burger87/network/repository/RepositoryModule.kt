package com.sahonmu.burger87.network.repository

import com.sahonmu.burger87.network.NetworkModule
import com.sahonmu.burger87.network.api.BusApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesBus(okHttpClient: OkHttpClient): BusApi =
        NetworkModule.providesBusRetrofit(okHttpClient).create(BusApi::class.java)

}