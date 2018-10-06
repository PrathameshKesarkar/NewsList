package co.pratham.newslist.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import co.pratham.newslist.data.model.NewsCollection

@Database(entities = [NewsCollection::class],exportSchema = false,version = 1)
@TypeConverters(value = [NewsItemConverter::class])
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDAO():NewsDAO
}