package co.pratham.newslist.data.source

import co.pratham.newslist.data.model.NewsCollection
import co.pratham.newslist.data.repository.NewsDataStore
import co.pratham.newslist.data.repository.NewsLocalSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class NewsLocalDataStore @Inject constructor(private val localSource: NewsLocalSource) : NewsDataStore {

    override fun clearNewsCollection(): Completable =
            localSource.clearNewsCollection()

    override fun saveNewsCollection(collection: NewsCollection): Completable =
            localSource.saveNewsCollecction(collection).doOnComplete{localSource.setLastCacheTime(System.currentTimeMillis())}

    override fun getNewsCollection(slug: String): Flowable<NewsCollection> =
            localSource.getNewsCollection(slug)

    override fun isCached(slug: String): Single<Boolean> =
            localSource.isCached(slug)
}
