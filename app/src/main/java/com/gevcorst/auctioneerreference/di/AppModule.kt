package com.gevcorst.auctioneerreference.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext

import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IODispatcher

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun providesCoroutineScope(): CoroutineScope {
        // Run this code when providing an instance of CoroutineScope
        return CoroutineScope(SupervisorJob() + Dispatchers.Default)
    }

    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}

