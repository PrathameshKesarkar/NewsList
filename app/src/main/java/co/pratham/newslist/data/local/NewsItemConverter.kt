package co.pratham.newslist.data.local

import android.arch.persistence.room.TypeConverter
import co.pratham.newslist.data.model.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class NewsItemConverter{
    val gson = Gson()

    @TypeConverter
    fun stringToItemList(data:String?):List<Item>{
        if(data==null){
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<Item>>() {}.type
        return gson.fromJson(data,listType)
    }

    @TypeConverter
    fun itemListToString(items:List<Item>):String = gson.toJson(items)
}