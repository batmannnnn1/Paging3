package com.example.paging3.repo

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingSourceFactory
import androidx.paging.liveData
import com.example.paging3.Paging.QuoteRemoteMediator
import com.example.paging3.Retrofit.QuoteApi
import com.example.paging3.db.QuoteDatabase
import javax.inject.Inject

class Repository @Inject constructor(val quoteApi: QuoteApi,val quoteDatabase: QuoteDatabase) {

    @OptIn(ExperimentalPagingApi::class)
    fun getQuotes() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = {quoteDatabase.getQuoteDao().getQuote()},
        remoteMediator = QuoteRemoteMediator(quoteApi,quoteDatabase)

    ).liveData


}