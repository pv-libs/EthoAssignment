package com.assingment.etho.connection.mock

import com.assingment.etho.connection.ClientConnection
import com.assingment.etho.connection.Connection
import com.assingment.etho.connection.ServerConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MockConnectionModule {

    @Provides
    @ServerConnection
    fun provideServerConnection(
        mockConnection: MockConnection
    ): Connection = mockConnection.getServerConnection()

    @Provides
    @ClientConnection
    fun provideClientConnection(
        mockConnection: MockConnection
    ): Connection = mockConnection.getClientConnection()

}