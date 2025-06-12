package com.example.preparcialayd.model.external

import com.google.gson.Gson
import com.google.gson.JsonObject
import java.net.URL

const val API_URL = "https://api.alternative.me/v2/ticker/1/?convert="

class FreeCryptoAPI {

    fun getPrice(symbol: String): Double {
        val url = buildUrl(symbol)
        val response = fetchApiResponse(url)
        return extractPriceFromJson(response, symbol)
    }

    private fun buildUrl(symbol: String): String {
        return API_URL + "$symbol"
    }

    private fun fetchApiResponse(url: String): String {
        return URL(url).readText()
    }

    private fun extractPriceFromJson(json: String, symbol: String): Double {
        val jsonObject = Gson().fromJson(json, JsonObject::class.java)
        val data = jsonObject["data"].asJsonObject
        val firstElement = data["1"].asJsonObject
        val quotes = firstElement["quotes"].asJsonObject
        val symbolElement = quotes[symbol].asJsonObject
        val price = symbolElement["price"]
        return price.asDouble
    }
}