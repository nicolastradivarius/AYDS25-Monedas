package com.example.preparcialayd.injector

import android.content.Context
import android.content.SharedPreferences
import com.example.preparcialayd.model.CryptoRepository
import com.example.preparcialayd.model.CryptoRepositoryImpl
import com.example.preparcialayd.model.external.CryptoExternal
import com.example.preparcialayd.model.external.CryptoExternalImpl
import com.example.preparcialayd.model.external.FreeCryptoAPI
import com.example.preparcialayd.model.local.CryptoLocal
import com.example.preparcialayd.model.local.CryptoLocalImpl
import com.example.preparcialayd.presenter.CryptoPresenter
import com.example.preparcialayd.presenter.CryptoPresenterImpl

object CryptoInjector {
    lateinit var api: FreeCryptoAPI
    lateinit var repository: CryptoRepository
    lateinit var localStorage: CryptoLocal
    lateinit var externalStorage: CryptoExternal
    lateinit var presenter: CryptoPresenter
    lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("MY_SHARED_PREFERENCES", Context.MODE_PRIVATE)
        api = FreeCryptoAPI()
        localStorage = CryptoLocalImpl(sharedPreferences)
        externalStorage = CryptoExternalImpl(api)
        repository = CryptoRepositoryImpl(localStorage, externalStorage)
        presenter = CryptoPresenterImpl(repository)
    }
}
