package com.example.appnews.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.appnews.R
import com.google.android.material.snackbar.Snackbar

fun ImageView.downloadAndSetImage(url: String?) {
    Glide
        .with(APP_ACTIVITY)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.placeholder)
        .into(this)
}

fun showSnackbar(view: View, text:String) {
    Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show()
}