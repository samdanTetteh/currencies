package com.example.currencies.Data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.currencies.BASE_URL
import com.example.currencies.LOG_TAG
import com.example.currencies.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Repository(val app : Application) {

    val currencyData = MutableLiveData<Currencies>()


    init {
        CoroutineScope(Dispatchers.IO)
            .launch {
                callWebService()
            }

    }


    @WorkerThread
    suspend fun callWebService(){
        if (isNetWorkAvailable()){
           val retrofit = Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(MoshiConverterFactory.create())
               .build();

            val service  = retrofit.create(CurrencyService::class.java)
            val serviceData = service.getCurrencies(app.resources.getString(R.string.base_currency)).body()
            Log.d(LOG_TAG, serviceData.toString())
            currencyData.postValue(serviceData)
        }else{
            withContext(Dispatchers.Main){
                Toast.makeText(app, "Please check your internet connection", Toast.LENGTH_LONG).show()
            }
        }
    }





    @Suppress("DEPRECATION")
    private fun isNetWorkAvailable() : Boolean{
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }


}