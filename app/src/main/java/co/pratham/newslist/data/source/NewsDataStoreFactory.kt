package co.pratham.newslist.data.source

import co.pratham.newslist.data.repository.NewsDataStore
import co.pratham.newslist.data.repository.NewsLocalSource
import javax.inject.Inject

class NewsDataStoreFactory @Inject constructor(private val localDataStore: NewsLocalDataStore,
                                                   private val remoteDataStore: NewsRemoteDataStore,
                                                   private val localSource: NewsLocalSource) {
    fun retrieveLocalDataStore() = localDataStore

    fun retrieveRemoteDataStore() = remoteDataStore


    /**
     * returns @NewsDataStore based on whether there is content in the cache
     * or whether cache is not Expired
     * */
    fun retrieveDataStore(isCached: Boolean, slug: String): NewsDataStore {

        //If the data is present in cache and not expired
        return if (isCached && !localSource.isExpired()) {
            retrieveLocalDataStore()
        }
        //When data is expired clear the cache and remote
        else if (isCached && localSource.isExpired()) {
            localSource.clearNewsCollection()
                    .to { retrieveRemoteDataStore() }
        } else {
            retrieveRemoteDataStore()
        }

    }

}