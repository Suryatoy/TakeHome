package com.surya.githubuserdetails.util

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.surya.githubuserdetails.R
import java.text.SimpleDateFormat


class GenericUtils {

    companion object {

        /**
         * To load images into Image view
         */
        fun loadImage(url: String?, imageView: ImageView, context: Context) {
            Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView)
        }

        /**
         * To convert date string  to timestamp format MMM d,yyyy HH:mm:ss a
         */
        fun convertTimestamp(updatedDate: String?): String {
            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val formatter = SimpleDateFormat("MMM d,yyyy HH:mm:ss a")
            return formatter.format(parser.parse(updatedDate))
        }

        /**
         * To hide keyboard
         */
        fun hideSoftKeyBoard(context: Context, view: View) {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }

        /**
         * To show error in network calls snackbar
         */
        fun showError(view: View, context: Context) {
            val snackbar = Snackbar.make(
                view, context.getString(R.string.network_error),
                Snackbar.LENGTH_LONG
            ).setAction(context.getString(R.string.snackbar_ok), null)
            snackbar.setActionTextColor(Color.WHITE)
            val snackbarView = snackbar.view
            snackbarView.setBackgroundColor(Color.BLACK)
            snackbar.show()
        }
    }
}