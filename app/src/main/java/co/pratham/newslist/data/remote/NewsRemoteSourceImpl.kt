package co.pratham.newslist.data.remote

import co.pratham.newslist.data.model.NewsCollection
import co.pratham.newslist.data.repository.NewsRemoteSource
import io.reactivex.Flowable
import javax.inject.Inject

class NewsRemoteSourceImpl @Inject constructor(private val apiService: NewsEndpointService) : NewsRemoteSource {

    override fun getNewsCollection(collectionName: String): Flowable<NewsCollection> =
            apiService.getNewsCollection(collectionName)

}