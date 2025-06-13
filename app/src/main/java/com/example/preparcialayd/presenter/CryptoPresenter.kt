package com.example.preparcialayd.presenter

import ayds.observer.Observable
import ayds.observer.Subject
import com.example.preparcialayd.model.CryptoRepository
import kotlin.concurrent.thread
import kotlin.math.roundToInt

interface CryptoPresenter {
    val presenterObservable: Observable<Pair<String, Int>>

    fun fetchPrice(monedaSeleccionada: String)
    fun getMonedas(): List<String>
}

class CryptoPresenterImpl(
    private val repository: CryptoRepository
): CryptoPresenter {

    // TODO: pasar de Pair a UIState
    override val presenterObservable = Subject<Pair<String, Int>>()

    override fun fetchPrice(monedaSeleccionada: String) {
        thread {
            repository.fetchPrice(monedaSeleccionada).let {
                presenterObservable.notify(Pair(monedaSeleccionada, it.roundToInt()))
            }
        }
    }

    override fun getMonedas(): List<String> {
        return repository.getSymbols()
    }
}