package com.assingment.etho.data_manager.client

import com.assingment.etho.data_manager.client.data_store.ClientDataStoreHelper
import com.assingment.etho.data_manager.client.data_store.ClientDataStoreHelperImpl
import com.assingment.etho.data_manager.client.p2p.ClientConnectionHelper
import com.assingment.etho.data_manager.client.p2p.ClientConnectionHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ClientDataManagerModule {

    @Binds
    abstract fun bindDataStoreHelper(
        clientDataStoreHelperImpl: ClientDataStoreHelperImpl
    ): ClientDataStoreHelper

    @Binds
    abstract fun bindConnectionHelper(
        clientConnectionHelperImpl: ClientConnectionHelperImpl
    ): ClientConnectionHelper


    @Binds
    abstract fun bindDataManager(
        clientDataManagerImpl: ClientDataManagerImpl
    ): ClientDataManager

}