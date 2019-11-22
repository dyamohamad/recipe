package com.example.fourtitudeasia.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.work.cyberbully.ui.main.GlideApp

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {

    GlideApp.with(imageView.context)
        .load(url)
        .into( imageView)
}