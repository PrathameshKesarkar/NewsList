package co.pratham.newslist.home.di

import android.arch.lifecycle.ViewModelProviders
import co.pratham.newslist.data.NewsDataRepository
import co.pratham.newslist.data.repository.NewsRepository
import co.pratham.newslist.data.source.NewsDataStoreFactory
import co.pratham.newslist.di.scopes.PerActivity
import co.pratham.newslist.home.activity.HomeActivity
import co.pratham.newslist.home.viewmodel.HomeViewModel
import co.pratham.newslist.home.viewmodel.HomeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    @PerActivity
    fun provideRepository(dataFactory: NewsDataStoreFactory): NewsRepository =
            NewsDataRepository(dataFactory)

    @Provides
    @PerActivity
    fun providesViewModelFactory(repository: NewsRepository) = HomeViewModelFactory(repository)

    @Provides
    @PerActivity
    fun providesViewModel(viewModelFactory: HomeViewModelFactory,
                          activity: HomeActivity) =
            ViewModelProviders.of(activity, viewModelFactory).get(HomeViewModel::class.java)


}