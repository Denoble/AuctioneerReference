package com.gevcorst.auctioneerreference.di

import com.gevcorst.auctioneerreference .model.UserDataStoreImpl
import com.gevcorst.auctioneerreference .model.UserDataStore
import com.gevcorst.auctioneerreference .model.services.*
import com.gevcorst.auctioneerreference .model.services.AccountServiceImpl
import com.gevcorst.auctioneerreference .model.services.ConfigurationServiceImpl
import com.gevcorst.auctioneerreference .model.services.StorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun logServiceProvider(logService: LogServiceImpl): LogService
    @Binds
    abstract fun storageServiceProvider(impl: StorageServiceImpl): StorageService
    @Binds
    abstract fun accountServiceProvider(impl: AccountServiceImpl): AccountService
    @Binds
    abstract fun configurationServiceProvider(impl: ConfigurationServiceImpl): ConfigurationService
    @Binds
    abstract fun bindUserPreferences(impl: UserDataStoreImpl): UserDataStore
}
