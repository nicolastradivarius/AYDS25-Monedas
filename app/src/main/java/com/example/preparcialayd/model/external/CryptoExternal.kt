package com.example.preparcialayd.model.external

interface CryptoExternal {
    fun getPrice(symbol: String): Double
}

class CryptoExternalImpl(
    private val freeCryptoAPI: FreeCryptoAPI
): CryptoExternal {

    override fun getPrice(symbol: String): Double {
        return freeCryptoAPI.getPrice(symbol)
    }
}