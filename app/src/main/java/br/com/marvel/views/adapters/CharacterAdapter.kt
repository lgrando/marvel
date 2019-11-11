package br.com.marvel.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.marvel.R
import br.com.marvel.databinding.ItemCharacterBinding
import br.com.marvel.models.Character

class CharacterAdapter(
    private var list: List<Character>,
    private val listener: (Character) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCharacterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_character, parent, false
        )

        return ViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    class ViewHolder(private val binding: ItemCharacterBinding, private val listener: (Character) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Character) {
            binding.character = item
            itemView.setOnClickListener { listener(item) }
            binding.executePendingBindings()
        }
    }
}