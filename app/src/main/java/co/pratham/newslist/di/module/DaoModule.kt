package co.pratham.newslist.di.module

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import co.pratham.newslist.data.local.NewsDAO
import co.pratham.newslist.data.local.NewsDatabase
import co.pratham.newslist.data.local.NewsLocalSourceImpl
import co.pratham.newslist.data.local.PreferenceHelper
import co.pratham.newslist.data.repository.NewsLocalSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DaoModule {

    @Provides
    @Singleton
    fun provideNewsDatabase(context: Context) =
            Room.databaseBuilder(context, NewsDatabase::class.java, "news-db")
                    .build()

    @Provides
    @Singleton
    fun provideNewsDao(database: NewsDatabase) = database.newsDAO()

    @Provides
    @Singleton
    fun providesPref(context: Context) = PreferenceHelper(context)

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: NewsDAO, prefHelper: PreferenceHelper): NewsLocalSource =
            NewsLocalSourceImpl(prefHelper, dao)


}