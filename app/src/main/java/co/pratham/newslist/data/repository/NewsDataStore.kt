package co.pratham.newslist.data.repository

import co.pratham.newslist.data.model.NewsCollection
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Default implementation is to throw Exception for class that don't implement it
 * So we don't have to Implement Function in all the classes.
 * */
interface NewsDataStore {

    fun clearNewsCollection(): Completable =
            throw UnsupportedOperationException()

    fun saveNewsCollection(collection: NewsCollection): Completable =
            throw  UnsupportedOperationException()

    fun getNewsCollection(slug: String): Flowable<NewsCollection>

    fun isCached(slug: String): Single<Boolean> =
            throw  UnsupportedOperationException()
}