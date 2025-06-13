package com.example.preparcialayd.presenter

import ayds.observer.Observable
import ayds.observer.Subject
import com.example.preparcialayd.model.CryptoRepository
import com.example.preparcialayd.view.CryptoUIState
import kotlin.concurrent.thread
import kotlin.math.roundToInt

interface CryptoPresenter {
    val presenterObservable: Observable<CryptoUIState>

    fun fetchPrice(monedaSeleccionada: String)
    fun getMonedas(): List<String>
}

class CryptoPresenterImpl(
    private val repository: CryptoRepository
): CryptoPresenter {

    override val presenterObservable = Subject<CryptoUIState>()

    override fun fetchPrice(monedaSeleccionada: String) {
        thread {
            repository.fetchPrice(monedaSeleccionada).let {
                presenterObservable.notify(CryptoUIState(monedaSeleccionada, it.roundToInt()))
            }
        }
    }

    override fun getMonedas(): List<String> {
        return repository.getSymbols()
    }
}