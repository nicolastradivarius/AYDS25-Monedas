package com.example.preparcialayd.A

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.preparcialayd.B.SomeClass
import com.example.preparcialayd.R

class MainScreen : AppCompatActivity() {
    private val dependency = SomeClass(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dependency.observer.subscribe { result ->
            onPrice(result.first, result.second)
        }

        val spinner = findViewById<Spinner>(R.id.spinnerMonedas)
        val monedas = listOf("USD", "EUR", "CAD", "JPY")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, monedas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val monedaSeleccionada = monedas[position]
                dependency.fetchPrice(monedaSeleccionada)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

    }

    fun onPrice(symbol: String, price: Int) {
        val mensaje = "$symbol – $price"
        runOnUiThread {
            findViewById<TextView>(R.id.textPrecio).text = mensaje
        }
    }
}