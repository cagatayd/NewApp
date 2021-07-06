package com.cagatay.newapp.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class GlideHelper {

    companion object {

        fun loadImage(context: Context, url: String, imageView: ImageView?) {
            Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .centerCrop()
                .into(imageView!!)
        }
    }
}