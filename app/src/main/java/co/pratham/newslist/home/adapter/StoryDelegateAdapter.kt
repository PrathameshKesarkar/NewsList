package co.pratham.newslist.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import co.pratham.newslist.R
import co.pratham.newslist.base.adapter.ViewType
import co.pratham.newslist.base.adapter.ViewTypeDelegateAdapter
import co.pratham.newslist.base.view.inflate
import co.pratham.newslist.data.model.Item
import co.pratham.newslist.di.module.GlideApp
import de.hdodenhof.circleimageview.CircleImageView
import io.reactivex.subjects.PublishSubject

class StoryDelegateAdapter(private val publishSubject: PublishSubject<Item>) : ViewTypeDelegateAdapter {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as StoryViewHolder
        holder.bind(item as Item)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
            StoryViewHolder(parent)

    inner class StoryViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item)) {
        private val ivNewsHeader = itemView.findViewById<ImageView>(R.id.iv_news_header)
        private val tvNews = itemView.findViewById<TextView>(R.id.tv_news)
        private val tvName = itemView.findViewById<TextView>(R.id.tv_author)
        private val tvSummary = itemView.findViewById<TextView>(R.id.tv_summary)
        fun bind(item: Item) {
            tvName.text = item.story.authorName
            tvNews.text = item.story.headline
            tvSummary.text = item.story.summary
            GlideApp
                    .with(itemView.context)
                    .load(item.story.heroImage)
                    .placeholder(android.R.color.darker_gray)
                    .centerCrop()
                    .into(ivNewsHeader)

            itemView.setOnClickListener { publishSubject.onNext(item) }

        }
    }


}