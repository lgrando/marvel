package br.com.marvel.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import br.com.marvel.R
import br.com.marvel.databinding.ActivityCharacterDetailBinding
import br.com.marvel.models.Character
import br.com.marvel.models.Comic
import br.com.marvel.viewmodels.CharacterDetailViewModel
import br.com.marvel.views.adapters.ComicAdapter
import kotlinx.android.synthetic.main.activity_character_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterDetailActivity : AppCompatActivity() {

    private val viewModel: CharacterDetailViewModel by viewModel()
    private lateinit var binding: ActivityCharacterDetailBinding

    companion object {
        const val EXTRA_CHARACTER = "EXTRA_CHARACTER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityCharacterDetailBinding>(
            this,
            R.layout.activity_character_detail
        )

        configureObservers()
        configureExtras()
    }

    private fun configureObservers() {
        viewModel.run {
            comics.observe(this@CharacterDetailActivity, Observer { result ->
                prepareRecycler(result.data.results)
            })
            error.observe(this@CharacterDetailActivity, Observer {

            })
        }
    }

    private fun configureExtras() {
        if (intent.hasExtra(EXTRA_CHARACTER)) {
            val character = intent.extras?.get(EXTRA_CHARACTER) as Character
            binding.character = character
            viewModel.getCharacterComicsList(character.id)
        } else {
            //TODO: Error on set layout by extra
        }
    }

    private fun prepareRecycler(comicsList: List<Comic>) {
        val adapter = ComicAdapter(comicsList)
        rvComics.adapter = adapter
    }
}
