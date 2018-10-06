package co.pratham.newslist.data.remote

import co.pratham.newslist.data.model.NewsCollection
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsEndpointService{

    @GET("{collection}")
    fun getNewsCollection(@Path("collection")collectionName:String):Flowable<NewsCollection>
}