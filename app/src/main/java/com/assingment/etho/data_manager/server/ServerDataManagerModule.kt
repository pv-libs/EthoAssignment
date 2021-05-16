package com.assingment.etho.data_manager.server

import com.assingment.etho.data_manager.client.data_store.ServerDataStoreHelperImpl
import com.assingment.etho.data_manager.server.data_store.ServerDataStoreHelper
import com.assingment.etho.data_manager.server.p2p.ServerConnectionHelper
import com.assingment.etho.data_manager.server.p2p.ServerConnectionHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServerDataManagerModule {

    @Binds
    abstract fun bindDataStoreHelper(
        serverDataStoreHelperImpl: ServerDataStoreHelperImpl
    ): ServerDataStoreHelper

    @Binds
    abstract fun bindConnectionHelper(
        serverConnectionHelperImpl: ServerConnectionHelperImpl
    ): ServerConnectionHelper


    @Binds
    abstract fun bindDataManager(
        serverDataManagerImpl: ServerDataManagerImpl
    ): ServerDataManager

}