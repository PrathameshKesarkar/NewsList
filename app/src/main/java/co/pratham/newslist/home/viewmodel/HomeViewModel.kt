package co.pratham.newslist.home.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import co.pratham.newslist.data.model.Item
import co.pratham.newslist.data.model.NewsCollection
import co.pratham.newslist.data.repository.NewsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    val newsLiveData = MutableLiveData<NewsCollection>()
    val updatedLiveData = MutableLiveData<NewsCollection>()
    private lateinit var homeCollection: Iterator<Item>
    fun fetchNews(slug: String) {
        repository
                .getNewsCollection(slug)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //Update the UI for the app
                    newsLiveData.postValue(it);
                    //Collect all the collections
                    homeCollection = it.items.filter { item -> item.type == "collection" }.iterator()
                    //Request story for collections
                    fetchMoreCollection(homeCollection.next().slug)
                },
                        Throwable::printStackTrace)
    }

    private fun fetchMoreCollection(slug: String) {
        repository
                .getNewsCollection(slug)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //Update the UI
                    updatedLiveData.postValue(it)
                    //Check if iterator has any items if so fetch data for those collections
                    if (homeCollection.hasNext()) {
                        fetchMoreCollection(homeCollection.next().slug)
                    }
                }, Throwable::printStackTrace)
    }
}