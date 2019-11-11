package br.com.marvel.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import br.com.marvel.R
import br.com.marvel.databinding.ActivityCharacterDetailBinding
import br.com.marvel.models.Character

class CharacterDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CHARACTER = "EXTRA_CHARACTER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityCharacterDetailBinding>(
            this,
            R.layout.activity_character_detail
        )

        if (intent.hasExtra(EXTRA_CHARACTER)) {
            val character = intent.extras?.get(EXTRA_CHARACTER) as Character
            binding.character = character
        } else {
            //TODO: Error on set layout by extra
        }
    }
}
