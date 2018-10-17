package com.teaml.chargingmonitor.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("backgroundRes")
    fun setBackgroundRes(img: ImageView, color: Int) {
        img.setImageResource(color)
    }
}