package com.example.paging3.Retrofit

import com.example.paging3.Model.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page:Int):QuoteList
}