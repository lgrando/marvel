package br.com.marvel.extensions

import android.widget.TextView
import androidx.databinding.BindingAdapter
import br.com.marvel.R

@BindingAdapter("textWithPlaceHolder")
fun TextView.setTextWithPlaceHolder(text: String) {
    if (text.isEmpty()) {
        this.text = context.getString(R.string.text_place_holder_label)
    } else {
        this.text = text
    }
}