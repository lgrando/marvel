package br.com.marvel

import br.com.marvel.datasources.MarvelRemoteDataSource
import br.com.marvel.repositories.MarvelRepository
import br.com.marvel.services.MarvelService
import br.com.marvel.utils.getApi
import br.com.marvel.viewmodels.CharacterListViewModel
import br.com.marvel.viewmodels.CharacterDetailViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val marvelModule = module {
    factory { getApi(MarvelService::class.java) }
    factory { MarvelRemoteDataSource(get()) }
    factory { MarvelRepository(get()) }
    viewModel {
        CharacterListViewModel(
            get(), Dispatchers.IO
        )
    }
    viewModel {
        CharacterDetailViewModel(
            get(), Dispatchers.IO
        )
    }
}