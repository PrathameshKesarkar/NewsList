package co.pratham.newslist.data.repository

import co.pratham.newslist.data.model.NewsCollection
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface NewsLocalSource {

    fun getNewsCollection(collectionName: String): Flowable<NewsCollection>

    fun saveNewsCollecction(newsCollection: NewsCollection): Completable

    fun clearNewsCollection(): Completable

    fun isCached(collectionName: String): Single<Boolean>

    fun setLastCacheTime(lastCache: Long)
    fun isExpired(): Boolean
}