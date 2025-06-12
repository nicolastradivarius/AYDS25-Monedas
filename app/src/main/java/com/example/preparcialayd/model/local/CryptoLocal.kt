package com.example.preparcialayd.model.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

interface CryptoLocal {
    fun getPrice(symbol: String): String?
    fun addPrice(symbol: String, price: Double)
}

class CryptoLocalImpl(
    context: Context
): CryptoLocal {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MY_SHARED_PREFERENCES", Context.MODE_PRIVATE)

    override fun getPrice(symbol: String): String? {
        return sharedPreferences.getString(symbol, null)
    }

    override fun addPrice(symbol: String, price: Double) {
        sharedPreferences.edit { putString(symbol, price.toString()) }
    }
}