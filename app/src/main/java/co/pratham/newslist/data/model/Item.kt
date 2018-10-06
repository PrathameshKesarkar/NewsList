package co.pratham.newslist.data.model

import co.pratham.newslist.base.adapter.AdapterConstants
import co.pratham.newslist.base.adapter.ViewType
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Item(
        @SerializedName("id")
        @Expose
        val id: String,

        @SerializedName("type")
        @Expose
        val type: String,

        @SerializedName("name")
        @Expose
        val name: String,

        @SerializedName("url")
        @Expose
        val url: String,

        @SerializedName("slug")
        @Expose
        val slug: String,

        @SerializedName("story")
        @Expose
        val story: Story) : ViewType {

    override fun getViewType(): Int {
        return if (type == "collection") {
            AdapterConstants.Header
        } else {
            AdapterConstants.STORY
        }
    }
}