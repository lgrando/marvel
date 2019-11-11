package br.com.marvel

import android.app.Application
import org.koin.core.context.startKoin

class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            modules(
                listOf(marvelModule)
            )
        }
    }
}