package com.example.calmsleep.model

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.io.File

object Utils {
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(iv: ImageView, path: String?) {
        if (path == null){
            return
        }
        Glide.with(iv.context)
            .load(File(path))
            .into(iv)
    }
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(iv: ImageView, imageId: Int) {
        iv.setImageResource(imageId)
    }
    @JvmStatic
    @BindingAdapter("setText")
    fun setText(tv: TextView, content: String?) {
        tv.text = content
    }
}