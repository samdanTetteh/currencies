package com.example.currencies.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.currencies.Data.Repository

class ViewModel (val app : Application) : AndroidViewModel(app) {

    val currencyRepository  = Repository(app)
    val currencyData = currencyRepository.currencyData





}