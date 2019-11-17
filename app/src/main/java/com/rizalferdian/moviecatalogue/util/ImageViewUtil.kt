package com.rizalferdian.moviecatalogue.util

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImage(uri: Uri) {
    Glide.with(this.context).load(uri).into(this)
}