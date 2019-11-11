package br.com.marvel.extensions

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String) {
    val circleProgressDrawable = CircularProgressDrawable(context)
    circleProgressDrawable.strokeWidth = 10f
    circleProgressDrawable.centerRadius = 50f
    circleProgressDrawable.start()

    Glide.with(context)
        .load(url)
        .placeholder(circleProgressDrawable)
        .into(this)
}