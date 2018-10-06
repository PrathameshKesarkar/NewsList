package co.pratham.newslist.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import co.pratham.newslist.R
import co.pratham.newslist.base.adapter.ViewType
import co.pratham.newslist.base.adapter.ViewTypeDelegateAdapter
import co.pratham.newslist.base.view.inflate

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            LoadingViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    inner class LoadingViewHolder(val parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.loading_item)
    )
}

