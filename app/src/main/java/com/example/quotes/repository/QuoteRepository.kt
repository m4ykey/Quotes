package com.example.quotes.repository

import com.example.quotes.api.QuotesApi
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api : QuotesApi
) {

    suspend fun getQuotes() = api.getQuotes()
}