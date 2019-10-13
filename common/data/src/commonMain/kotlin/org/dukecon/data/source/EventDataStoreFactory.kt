package org.dukecon.data.source

import org.dukecon.data.repository.EventDataStore

open class EventDataStoreFactory constructor(
        private val eventCacheDataStore: EventCacheDataStore,
        private val eventRemoteDataStore: EventRemoteDataStore
) {

    /**
     * Returns a DataStore based on whether or not there is content in the cache and the cache
     * has not expired
     */
    open fun retrieveDataStore(isCached: Boolean, hasInternetConnected: Boolean): EventDataStore {
        if (hasInternetConnected) {
            if (isCached) {
                return retrieveCacheDataStore()
            } else {
                return retrieveRemoteDataStore()
            }
        }
        return retrieveCacheDataStore()
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveCacheDataStore(): EventDataStore {
        return eventCacheDataStore
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveRemoteDataStore(): EventDataStore {
        return eventRemoteDataStore
    }
}