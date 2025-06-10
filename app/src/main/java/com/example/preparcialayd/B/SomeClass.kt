package com.example.preparcialayd.B

import android.content.Context
import android.util.Log
import ayds.observer.Subject
import com.example.preparcialayd.C.DataRepo
import kotlin.concurrent.thread
import kotlin.math.roundToInt

class SomeClass(context: Context) {

    val observer = Subject<Pair<String, Int>>()
    private val repository = DataRepo(context)

    fun fetchPrice(monedaSeleccionada: String) {
        thread {
            val result = repository.fetchPrice(monedaSeleccionada)
            Log.e("RESULT", result.toString())
            observer.notify(Pair(monedaSeleccionada, result.roundToInt()))
        }
    }
}