package com.example.preparcialayd.model

import com.example.preparcialayd.model.external.CryptoExternal
import com.example.preparcialayd.model.local.CryptoLocal

interface CryptoRepository {
    fun fetchPrice(symbol: String): Double
    fun getSymbols(): List<String>
}

class CryptoRepositoryImpl(
    private val cryptoLocal: CryptoLocal,
    private val cryptoExternal: CryptoExternal
): CryptoRepository {

    val simbolosMonedas = listOf("USD", "EUR", "CAD", "JPY", "RUB", "GBP", "KRW", "PLN")

    override fun fetchPrice(symbol: String): Double {
        var cachedPrice = cryptoLocal.getPrice(symbol)
        var price: Double

        // repository pattern
        if (cachedPrice == null) {
            price = cryptoExternal.getPrice(symbol)
            cryptoLocal.addPrice(symbol, price)
        } else try {
            price = cachedPrice.toDouble()
        } catch (e: Exception) {
            price = 0.0
        }

        return price
    }

    override fun getSymbols(): List<String> {
        return simbolosMonedas
    }
}