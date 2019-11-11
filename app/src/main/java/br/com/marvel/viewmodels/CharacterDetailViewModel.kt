package br.com.marvel.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marvel.models.Comic
import br.com.marvel.models.MarvelResponse
import br.com.marvel.repositories.MarvelRepository
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CharacterDetailViewModel(
    private val repository: MarvelRepository,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    private val _comics = MutableLiveData<MarvelResponse<Comic>>()
    val comics: LiveData<MarvelResponse<Comic>> get() = _comics

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getCharacterComicsList(characterId: Long) {
        viewModelScope.launch(coroutineContext) {
            try {
                val response = repository.getCharacterComicsList(characterId)
                _comics.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }
}