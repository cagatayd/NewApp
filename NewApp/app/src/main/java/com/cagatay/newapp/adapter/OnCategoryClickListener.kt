package com.cagatay.newapp.adapter

import android.view.View
import com.cagatay.newapp.model.articles

interface OnCategoryClickListener {

    fun onClick(v: View, category : String)
}