package br.com.marvel.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marvel.models.Character
import br.com.marvel.models.MarvelResponse
import br.com.marvel.repositories.MarvelRepository
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CharacterListViewModel(
    private val repository: MarvelRepository,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    private val _characters = MutableLiveData<MarvelResponse<Character>>()
    val characters: LiveData<MarvelResponse<Character>> get() = _characters

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getCharactersList() {
        viewModelScope.launch(coroutineContext) {
            _isLoading.postValue(true)
            try {
                val response = repository.getCharactersList()
                _characters.postValue(response)
                _isLoading.postValue(false)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }
}