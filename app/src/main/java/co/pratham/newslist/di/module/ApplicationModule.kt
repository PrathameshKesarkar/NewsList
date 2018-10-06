package co.pratham.newslist.di.module

import android.app.Application
import android.content.Context
import co.pratham.newslist.NewsListApplication
import co.pratham.newslist.di.scopes.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule{

    @Provides
    @Singleton
    fun providesApplicationContext(application: NewsListApplication): Context {
        return application
    }


}