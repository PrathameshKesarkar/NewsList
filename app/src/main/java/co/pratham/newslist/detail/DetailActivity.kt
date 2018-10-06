package co.pratham.newslist.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.pratham.newslist.R
import co.pratham.newslist.di.module.GlideApp
import co.pratham.newslist.utils.IMAGE_URL
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val imageUrl = intent.getStringExtra(IMAGE_URL)
        GlideApp.with(this)
                .load(imageUrl)
                .centerCrop()
                .placeholder(android.R.color.darker_gray)
                .into(iv_hero)
    }
}