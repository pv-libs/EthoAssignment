package com.assingment.etho.data_manager.client

import com.assingment.etho.data_manager.client.data_store.ClientDataStoreHelper
import com.assingment.etho.data_manager.client.p2p.ClientConnectionHelper
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ClientDataManagerImpl @Inject constructor(
    dataStoreHelper: ClientDataStoreHelper,
    connectionHelper: ClientConnectionHelper
) : ClientDataManager, ClientDataStoreHelper by dataStoreHelper,
    ClientConnectionHelper by connectionHelper {

}