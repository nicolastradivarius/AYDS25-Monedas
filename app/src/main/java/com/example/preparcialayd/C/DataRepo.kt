package com.example.preparcialayd.C

import C.ApiX
import C.ApiY
import Config
import android.content.Context

class DataRepo(private val context:Context) {

    fun fetchPrice(symbol: String): Double {
        return  if (Config.useX) ApiX(context).get(symbol) else ApiY().get(symbol)
    }
}