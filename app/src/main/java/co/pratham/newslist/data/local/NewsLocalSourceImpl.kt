package co.pratham.newslist.data.local

import co.pratham.newslist.data.model.NewsCollection
import co.pratham.newslist.data.repository.NewsLocalSource
import io.reactivex.Completable
import io.reactivex.Completable.complete
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class NewsLocalSourceImpl @Inject constructor(private val preference: PreferenceHelper,
                                              private val dao: NewsDAO) : NewsLocalSource {
    private val EXPIRATION_TIME = (864 * 100 * 1000).toLong()


    override fun getNewsCollection(collectionName: String): Flowable<NewsCollection> =
            Flowable.defer { Flowable.just(dao.getNewsCollection(collectionName)) }


    override fun saveNewsCollecction(newsCollection: NewsCollection): Completable =
            Completable.defer {
                dao.saveNewsCollection(newsCollection)
                return@defer complete()
            }


    override fun clearNewsCollection(): Completable =
            Completable.defer {
                dao.clearAllNewsCollection()
                return@defer complete()
            }

    @Suppress("SENSELESS_COMPARISON")
    override fun isCached(collectionName: String): Single<Boolean> =
            Single.defer {
                Single.just(dao.getNewsCollection(collectionName) != null)
            }

    override fun isExpired(): Boolean {
        val currentCachedTime = System.currentTimeMillis()
        val lastCachedTime = preference.getLastCacheTime()
        return (currentCachedTime - lastCachedTime) > EXPIRATION_TIME
    }

    override fun setLastCacheTime(lastCache: Long) {
        preference.setLastCachedTime(lastCache)
    }
}