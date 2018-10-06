package co.pratham.newslist.home.activity

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import co.pratham.newslist.R
import co.pratham.newslist.data.model.Item
import co.pratham.newslist.detail.DetailActivity
import co.pratham.newslist.home.adapter.NewsAdapter
import co.pratham.newslist.home.viewmodel.HomeViewModel
import co.pratham.newslist.utils.IMAGE_URL
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_news.setHasFixedSize(true)
        rv_news.adapter = NewsAdapter()

        (rv_news.adapter as NewsAdapter).clickSubject
                .subscribe({
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra(IMAGE_URL, it.story.heroImage)
                    startActivity(intent)
                }, Throwable::printStackTrace)

        viewModel.fetchNews("collection")

        viewModel.newsLiveData.observe(this, Observer {
            it?.let {
                val adapter = rv_news.adapter as NewsAdapter
                adapter.addItem(it)
            }
        })

        viewModel.updatedLiveData.observe(this, Observer {

            it?.let {
                val adapter = rv_news.adapter as NewsAdapter
                adapter.updateItem(it)
            }
        })

    }
}