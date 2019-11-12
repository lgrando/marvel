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

    private val _isUpdating = MutableLiveData<Boolean>()
    val isUpdating: LiveData<Boolean> get() = _isUpdating

    fun getCharactersList(offset: Int = 0) {
        viewModelScope.launch(coroutineContext) {
            configureLoader(true, offset)
            try {
                val response = repository.getCharactersList(offset)
                _characters.postValue(response)
                configureLoader(false, offset)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }

    private fun configureLoader(showLoader: Boolean, offset: Int) {
        if (offset > 0) {
            _isUpdating.postValue(showLoader)
        } else {
            _isLoading.postValue(showLoader)
        }
    }
}