package co.pratham.newslist.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Story(
        @SerializedName("author-name")
        @Expose
        val authorName: String,

        @SerializedName("headline")
        @Expose
        val headline: String,

        @SerializedName("slug")
        @Expose
        val slug: String,

        @SerializedName("summary")
        @Expose
        val summary: String,

        @SerializedName("hero-image")
        @Expose
        val heroImage: String)