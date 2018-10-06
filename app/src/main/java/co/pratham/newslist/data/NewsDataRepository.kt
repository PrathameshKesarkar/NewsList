package co.pratham.newslist.data

import co.pratham.newslist.data.model.NewsCollection
import co.pratham.newslist.data.repository.NewsRepository
import co.pratham.newslist.data.source.NewsDataStoreFactory
import co.pratham.newslist.data.source.NewsLocalDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class NewsDataRepository @Inject constructor(private val factory: NewsDataStoreFactory) : NewsRepository {

    override fun getNewsCollection(slug: String): Flowable<NewsCollection> {
        return factory.retrieveLocalDataStore().isCached(slug)
                .flatMapPublisher { it -> Flowable.just(factory.retrieveDataStore(it, slug)) }
                .switchMap { dataStore ->
                    return@switchMap if (dataStore is NewsLocalDataStore) {
                        dataStore.getNewsCollection(slug)
                    } else {
                        dataStore.getNewsCollection(slug)
                                .map { it -> saveNewsWithSlug(it, slug) }
                                .flatMap { newCollection ->
                                    saveNewsCollection(newCollection)
                                            .toSingle { newCollection }
                                            .toFlowable()
                                }
                    }
                }

    }

    override fun saveNewsCollection(collection: NewsCollection): Completable =
            factory.retrieveLocalDataStore().saveNewsCollection(collection)


    private fun saveNewsWithSlug(newsCollection: NewsCollection, slug: String): NewsCollection =
            newsCollection.copy(slug = slug)

}