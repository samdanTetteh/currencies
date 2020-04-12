package com.example.currencies.Data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyService {

    @GET("latest")
    suspend fun getCurrencies(@Query("base") base : String) : Response<Currencies>

}