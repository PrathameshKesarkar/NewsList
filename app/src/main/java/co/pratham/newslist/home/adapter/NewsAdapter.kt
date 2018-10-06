package co.pratham.newslist.home.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import co.pratham.newslist.base.adapter.AdapterConstants
import co.pratham.newslist.base.adapter.ViewType
import co.pratham.newslist.base.adapter.ViewTypeDelegateAdapter
import co.pratham.newslist.data.model.Item
import co.pratham.newslist.data.model.NewsCollection
import io.reactivex.subjects.PublishSubject

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: ArrayList<ViewType>
    private val delegateAdapters: SparseArrayCompat<ViewTypeDelegateAdapter> = SparseArrayCompat()
    val clickSubject = PublishSubject.create<Item>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.STORY, StoryDelegateAdapter(clickSubject))
        delegateAdapters.put(AdapterConstants.Header, HeaderDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)

    }

    fun addItem(collection: NewsCollection) {
        items.removeAt(0)
        items.addAll(collection.items)
        notifyItemRemoved(0)
        notifyItemRangeInserted(0, collection.items.size)
    }

    fun updateItem(collection: NewsCollection) {
        val itemPos = items.indexOfFirst { item -> (item as Item).slug == collection.slug }
        items.addAll(itemPos + 1, collection.items)
        notifyItemRangeInserted(itemPos + 1, collection.items.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return checkNotNull(delegateAdapters[viewType]?.onCreateViewHolder(parent))
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, pos: Int) {
        delegateAdapters[getItemViewType(pos)]?.onBindViewHolder(viewHolder, items[pos])
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].getViewType()
    }
}