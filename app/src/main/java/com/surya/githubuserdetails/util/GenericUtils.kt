package com.surya.githubuserdetails.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.surya.githubuserdetails.R
import java.text.SimpleDateFormat

class GenericUtils {

    companion object {
        // TO load image into Image view
        fun loadImage(url: String?, imageView: ImageView, context: Context) {
            Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView)
        }

        fun convertTimestamp(updatedDate: String?): String {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val formatter = SimpleDateFormat("MMM d,yyyy HH:mm:ss a")
            return formatter.format(parser.parse(updatedDate))
        }
    }
}