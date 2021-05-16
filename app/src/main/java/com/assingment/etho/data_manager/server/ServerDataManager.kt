package com.assingment.etho.data_manager.server

import com.assingment.etho.data_manager.server.data_store.ServerDataStoreHelper
import com.assingment.etho.data_manager.server.p2p.ServerConnectionHelper

interface ServerDataManager : ServerDataStoreHelper, ServerConnectionHelper {
}