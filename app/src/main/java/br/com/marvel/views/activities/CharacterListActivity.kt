package br.com.marvel.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import br.com.marvel.R
import br.com.marvel.models.Character
import br.com.marvel.viewmodels.CharacterListViewModel
import br.com.marvel.views.adapters.CharacterAdapter
import kotlinx.android.synthetic.main.activity_character_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterListActivity : AppCompatActivity() {

    private val viewModel: CharacterListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        configureObservers()
        viewModel.getCharactersList()
    }

    private fun configureObservers() {
        viewModel.run {
            characters.observe(this@CharacterListActivity, Observer { result ->
                prepareRecycler(result.data.results)
            })
            error.observe(this@CharacterListActivity, Observer {
                Log.d("Request result", "failure")
            })
        }
    }

    private fun prepareRecycler(characterList: List<Character>) {
        val adapter = CharacterAdapter(characterList)
        rvCharacters.adapter = adapter
    }
}
