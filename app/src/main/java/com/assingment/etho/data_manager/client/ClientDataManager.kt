package com.assingment.etho.data_manager.client

import com.assingment.etho.data_manager.client.data_store.ClientDataStoreHelper
import com.assingment.etho.data_manager.client.p2p.ClientConnectionHelper


/**
 * DataManager is made as a single point access by gathering all different types of
 * data sources (like DataBase, SharedPref/DataStore, Apis, P2P connection etc..)
 * */
interface ClientDataManager : ClientDataStoreHelper, ClientConnectionHelper