package co.pratham.newslist.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import co.pratham.newslist.utils.LAST_CACHE
import co.pratham.newslist.utils.NEWS_PACKAGE

class PreferenceHelper(context: Context) {
    val pref: SharedPreferences = context.getSharedPreferences(NEWS_PACKAGE, MODE_PRIVATE)

    fun setLastCachedTime(time: Long) {
        pref.edit().putLong(LAST_CACHE, time).apply()
    }

    fun getLastCacheTime() =
            pref.getLong(LAST_CACHE, 0)
}