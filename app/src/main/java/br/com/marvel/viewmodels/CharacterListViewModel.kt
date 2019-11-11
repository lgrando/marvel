package br.com.marvel.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.marvel.repositories.MarvelRepository
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CharacterListViewModel(
    private val repository: MarvelRepository,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    private val _characters = MutableLiveData<Any>()
    val characters: LiveData<Any> get() = _characters

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getCharactersList() {
        viewModelScope.launch(coroutineContext) {
            try {
                val response = repository.getCharactersList()
                _characters.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }
}