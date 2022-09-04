package com.example.quotes.api

import com.example.quotes.model.Quotes
import retrofit2.Response
import retrofit2.http.GET

interface QuotesApi {

    @GET("api/quotes")
    suspend fun getQuotes() : Response<List<Quotes>>
}