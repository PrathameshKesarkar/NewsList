package co.pratham.newslist.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import co.pratham.newslist.data.model.NewsCollection

@Dao
abstract class NewsDAO {

    @Query("Select * from news_collection")
    abstract fun getAllNewsCollection(): List<NewsCollection>

    @Query("Select * from news_collection where slug like :slug limit 1")
    abstract fun getNewsCollection(slug: String): NewsCollection

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveNewsCollection(collection: NewsCollection)

    @Query("Delete from news_collection")
    abstract fun clearAllNewsCollection()
}