package com.example.preparcialayd.C

import C.ApiY
import android.content.Context
import android.util.Log
import androidx.core.content.edit

class DataRepo(private val context:Context) {

    // Shared Preferences es como una cache en Android. Podemos tener diferentes SharedPreferences, por eso le ponemos un nombre.
    // En este caso MY_SHARED_PREFERENCES
    // Shared preferences se limpia al desinstalar o al limpiar el storage desde las preferencias de la app
    val sharedPreferences = context.getSharedPreferences("MY_SHARED_PREFERENCES", Context.MODE_PRIVATE)

    fun fetchPrice(symbol: String): Double {
        val savedValue = sharedPreferences.getString(symbol, null)

        if(savedValue != null) {
            Log.e("RESULT", "Value from shared Preferences")
            return try {
               savedValue.toDouble()
           } catch (e: Exception) {
               0.0
           }
        } else {
            Log.e("RESULT", "Value from service")
            val result = ApiY().get(symbol)
            sharedPreferences.edit { putString(symbol, result.toString()) }
            return result
        }
    }
}