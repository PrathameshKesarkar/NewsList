package co.pratham.newslist.data.repository

import co.pratham.newslist.data.model.NewsCollection
import io.reactivex.Completable
import io.reactivex.Flowable

interface NewsRepository {

    fun getNewsCollection(slug: String): Flowable<NewsCollection>
    fun saveNewsCollection(collection: NewsCollection): Completable
}