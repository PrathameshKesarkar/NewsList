package co.pratham.newslist.di.module

import co.pratham.newslist.di.scopes.PerActivity
import co.pratham.newslist.home.activity.HomeActivity
import co.pratham.newslist.home.di.HomeModule
import co.pratham.newslist.home.di.NewsRepositoryModule
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(modules = [(HomeModule::class),(NewsRepositoryModule::class)])
    abstract fun homeActivity():HomeActivity
}