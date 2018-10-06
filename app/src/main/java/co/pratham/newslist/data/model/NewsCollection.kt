package co.pratham.newslist.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import android.support.annotation.NonNull
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "news_collection")
data class NewsCollection(

        @PrimaryKey(autoGenerate = true)
        val newsId: Int,
        @SerializedName("slug")
        @Expose
        @NonNull
        val slug: String,
        @SerializedName("name")
        @Expose
        val name: String,
        @SerializedName("summary")
        @Expose
        val summary: String,
        @SerializedName("items")

        @TypeConverters(NewsCollection::class)
        @Expose
        val items: List<Item>
)