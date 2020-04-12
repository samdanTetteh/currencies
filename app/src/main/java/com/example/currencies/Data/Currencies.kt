package com.example.currencies.Data

data class Currencies (
    val baseCurrency: String,
    val rates: Map<String, Double>
)