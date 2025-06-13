package com.example.preparcialayd.model.local

import android.content.SharedPreferences
import androidx.core.content.edit

interface CryptoLocal {
    fun getPrice(symbol: String): String?
    fun addPrice(symbol: String, price: Double)
}

class CryptoLocalImpl(
    private val sharedPreferences: SharedPreferences
): CryptoLocal {

    override fun getPrice(symbol: String): String? {
        return sharedPreferences.getString(symbol, null)
    }

    override fun addPrice(symbol: String, price: Double) {
        sharedPreferences.edit { putString(symbol, price.toString()) }
    }
}