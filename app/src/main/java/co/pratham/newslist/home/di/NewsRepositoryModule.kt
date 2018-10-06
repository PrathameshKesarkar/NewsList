package co.pratham.newslist.home.di

import co.pratham.newslist.data.remote.NewsEndpointService
import co.pratham.newslist.data.remote.NewsRemoteSourceImpl
import co.pratham.newslist.data.repository.NewsRemoteSource
import co.pratham.newslist.di.scopes.PerActivity
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class NewsRepositoryModule {

    @Provides
    @PerActivity
    fun provideNewsApiService(retrofit: Retrofit) =
            retrofit.create(NewsEndpointService::class.java)

    @Provides
    @PerActivity
    fun provideRemoteService(apiService: NewsEndpointService): NewsRemoteSource =
            NewsRemoteSourceImpl(apiService)
}