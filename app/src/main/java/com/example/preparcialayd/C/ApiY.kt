package C

import com.google.gson.Gson
import com.google.gson.JsonObject
import java.net.URL

class ApiY {
    fun get(symbol: String): Double {
        val url = "https://api.alternative.me/v2/ticker/1/?convert=$symbol"
        val text = URL(url).readText()
        print(text)
        val jsonObject = Gson().fromJson(text, JsonObject::class.java)
        val data = jsonObject["data"].asJsonObject
        val firstElement = data["1"].asJsonObject
        val quotes = firstElement["quotes"].asJsonObject
        val symbolElement = quotes[symbol].asJsonObject
        val price = symbolElement["price"]
        return price.asDouble
    }
}