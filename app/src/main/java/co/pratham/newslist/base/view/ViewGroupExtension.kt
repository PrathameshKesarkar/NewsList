package co.pratham.newslist.base.view

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


fun ViewGroup.inflate(@LayoutRes layoutFile: Int): View =
        LayoutInflater.from(this.context).inflate(layoutFile, this, false)
