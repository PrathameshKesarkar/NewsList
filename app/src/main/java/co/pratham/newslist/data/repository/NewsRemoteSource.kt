package co.pratham.newslist.data.repository

import co.pratham.newslist.data.model.NewsCollection
import io.reactivex.Flowable

interface NewsRemoteSource {

    fun getNewsCollection(collectionName:String): Flowable<NewsCollection>
}