package br.com.marvel.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.marvel.R
import br.com.marvel.databinding.ItemComicBinding
import br.com.marvel.models.Comic

class ComicAdapter(
    private var list: List<Comic>
) : RecyclerView.Adapter<ComicAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemComicBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_comic, parent, false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    class ViewHolder(
        private val binding: ItemComicBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Comic) {
            binding.comic = item
            binding.executePendingBindings()
        }
    }
}