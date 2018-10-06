package co.pratham.newslist.di

import co.pratham.newslist.NewsListApplication
import co.pratham.newslist.di.module.ActivityBuilder
import co.pratham.newslist.di.module.ApplicationModule
import co.pratham.newslist.di.module.RetrofitModule
import co.pratham.newslist.di.module.DaoModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(modules = [AndroidSupportInjectionModule::class,
    ActivityBuilder::class, DaoModule::class, ApplicationModule::class,
    RetrofitModule::class])
@Singleton
interface AppComponent : AndroidInjector<NewsListApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<NewsListApplication>()
}