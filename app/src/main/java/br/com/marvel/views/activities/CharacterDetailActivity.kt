package br.com.marvel.views.activities

import android.os.Bundle
import android.view.View
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
import kotlinx.android.synthetic.main.error_component.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterDetailActivity : AppCompatActivity() {

    private val viewModel: CharacterDetailViewModel by viewModel()
    private lateinit var binding: ActivityCharacterDetailBinding
    private lateinit var character: Character

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
                onLoadingStatusChange(false)
            })
            isLoading.observe(this@CharacterDetailActivity, Observer { isLoading ->
                onLoadingStatusChange(isLoading)
            })
        }
    }

    private fun configureExtras() {
        if (intent.hasExtra(EXTRA_CHARACTER)) {
            character = intent.extras?.get(EXTRA_CHARACTER) as Character
            binding.character = character
            viewModel.getCharacterComicsList(character.id)
        }
    }

    private fun prepareRecycler(comicsList: List<Comic>) {
        if (comicsList.isNotEmpty()) {
            val adapter = ComicAdapter(comicsList)
            rvComics.adapter = adapter
        } else {
            configureEmptyListLayout()
        }
    }

    private fun configureEmptyListLayout() {
        rvComics.visibility = View.INVISIBLE
        tvEmptyList.visibility = View.VISIBLE
    }

    private fun onLoadingStatusChange(isLoading: Boolean) {
        pbLoader.visibility = if (isLoading) View.VISIBLE else View.GONE
        rvComics.visibility = if (isLoading) View.INVISIBLE else View.VISIBLE
    }
}
