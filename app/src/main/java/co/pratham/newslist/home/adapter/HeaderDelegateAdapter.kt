package co.pratham.newslist.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import co.pratham.newslist.R
import co.pratham.newslist.base.adapter.ViewType
import co.pratham.newslist.base.adapter.ViewTypeDelegateAdapter
import co.pratham.newslist.base.view.inflate
import co.pratham.newslist.data.model.Item

class HeaderDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            HeaderViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as HeaderViewHolder
        holder.bind(item as Item)
    }

    inner class HeaderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.header_item)) {
        private val tvHeader = itemView.findViewById<TextView>(R.id.tv_header)

        fun bind(item: Item) {
            tvHeader.text = item.name
        }
    }
}