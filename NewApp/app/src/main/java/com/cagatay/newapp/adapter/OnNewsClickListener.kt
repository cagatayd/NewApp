package com.cagatay.newapp.adapter

import android.view.View
import com.cagatay.newapp.model.articles

interface OnNewsClickListener {

    fun onNewsClicked(v:View,news:articles)
}