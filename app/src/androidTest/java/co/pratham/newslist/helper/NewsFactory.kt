package co.pratham.newslist.helper

import co.pratham.newslist.data.model.NewsCollection
import java.util.*

/**
 * Factory to create fake Object
 * */
object NewsFactory {

    fun makeFakeNews(): NewsCollection {
        return NewsCollection(124,
                "home",
                "home",
                "Home for summary",
                Collections.emptyList())
    }
}