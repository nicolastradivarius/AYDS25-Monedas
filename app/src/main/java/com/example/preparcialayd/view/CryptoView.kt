package com.example.preparcialayd.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.preparcialayd.R
import com.example.preparcialayd.injector.CryptoInjector
import com.example.preparcialayd.presenter.CryptoPresenter

class CryptoView : AppCompatActivity() {
    private lateinit var cryptoPresenter: CryptoPresenter
    private lateinit var spinner: Spinner
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var textPrecio: TextView
    private lateinit var monedas: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initModule()
        initProperties()
        initListeners()
        initObservers()
    }

    private fun initModule() {
        CryptoInjector.init(this)
        cryptoPresenter = CryptoInjector.presenter
    }

    private fun initProperties() {
        monedas = cryptoPresenter.getMonedas()
        spinner = findViewById<Spinner>(R.id.spinnerMonedas)
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, monedas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        textPrecio = findViewById<TextView>(R.id.textPrecio)
    }

    private fun initListeners() {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val monedaSeleccionada = monedas[position]
                cryptoPresenter.fetchPrice(monedaSeleccionada)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun initObservers() {
        cryptoPresenter.presenterObservable.subscribe { result ->
            onPrice(result.first, result.second)
        }
    }

    fun onPrice(symbol: String, price: Int) {
        val mensaje = "$symbol – $price"
        runOnUiThread {
            textPrecio.text = mensaje
        }
    }
}