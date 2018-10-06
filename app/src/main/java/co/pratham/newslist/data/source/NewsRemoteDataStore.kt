package co.pratham.newslist.data.source

import co.pratham.newslist.data.model.NewsCollection
import co.pratham.newslist.data.repository.NewsDataStore
import co.pratham.newslist.data.repository.NewsRemoteSource
import io.reactivex.Flowable
import javax.inject.Inject

class NewsRemoteDataStore @Inject constructor(private val remoteSource: NewsRemoteSource) : NewsDataStore {

    override fun getNewsCollection(slug: String): Flowable<NewsCollection> =
            remoteSource.getNewsCollection(slug)

}