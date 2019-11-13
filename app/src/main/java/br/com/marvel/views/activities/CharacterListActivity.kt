package br.com.marvel.views.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.marvel.R
import br.com.marvel.models.Character
import br.com.marvel.models.Thumbnail
import br.com.marvel.viewmodels.CharacterListViewModel
import br.com.marvel.views.adapters.CharacterAdapter
import kotlinx.android.synthetic.main.activity_character_list.*
import kotlinx.android.synthetic.main.error_component.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterListActivity : AppCompatActivity() {

    private val viewModel: CharacterListViewModel by viewModel()
    private val characterList = arrayListOf<Character>()
    private lateinit var adapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        configureObservers()
        configureListeners()
        configureRecyclerAdapter()
        viewModel.getCharactersList()
    }

    private fun configureObservers() {
        viewModel.run {
            characters.observe(this@CharacterListActivity, Observer { result ->
                prepareRecycler(result.data.results)
            })
            error.observe(this@CharacterListActivity, Observer {
                configureErrorLayout()
            })
            isLoading.observe(this@CharacterListActivity, Observer { isLoading ->
                onLoadingStatusChange(isLoading)
            })
            isUpdating.observe(this@CharacterListActivity, Observer { isUpdating ->
                lavUpdating.visibility = if (isUpdating) View.VISIBLE else View.GONE
            })
        }
    }

    private fun configureListeners() {
        errorComponent.btnRetry.setOnClickListener {
            viewModel.getCharactersList()
        }
    }

    private fun configureRecyclerAdapter() {
        adapter = CharacterAdapter(characterList) { character ->
            goToCharacterDetail(character)
        }
        rvCharacters.layoutManager = GridLayoutManager(this, 3)
        rvCharacters.adapter = adapter
    }

    private fun prepareRecycler(list: List<Character>) {
        characterList.addAll(list)
        adapter.notifyDataSetChanged()
        var isUpdatingAdapter = true
        rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = rvCharacters.layoutManager as GridLayoutManager
                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()

                    if (isUpdatingAdapter) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            isUpdatingAdapter = false
                            viewModel.getCharactersList(characterList.size)
                        }
                    }
                }
            }
        })
    }

    private fun goToCharacterDetail(character: Character) {
        val detailIntent = Intent(this, CharacterDetailActivity::class.java)
        detailIntent.putExtra(CharacterDetailActivity.EXTRA_CHARACTER, character)
        startActivity(detailIntent)
    }

    private fun onLoadingStatusChange(isLoading: Boolean) {
        rvCharacters.visibility = if (isLoading) View.GONE else View.VISIBLE
        lavLoader.visibility = if (isLoading) View.VISIBLE else View.GONE
        errorComponent.visibility = View.GONE
    }

    private fun configureErrorLayout() {
        characterList.clear()
        rvCharacters.visibility = View.GONE
        lavLoader.visibility = View.GONE
        lavUpdating.visibility = View.GONE
        errorComponent.visibility = View.VISIBLE
    }
}
