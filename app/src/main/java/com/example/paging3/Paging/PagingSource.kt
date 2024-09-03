package com.example.paging3.Paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3.Model.QuoteList
import com.example.paging3.Model.Result
import com.example.paging3.Retrofit.QuoteApi

class PagingSource(val quoteApi: QuoteApi): PagingSource<Int,Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(it)?.nextKey?.minus(1)

            /*
             anchor position means user's last interacted page index
             closestPageToPosition means it gave the closest Page according to anchorPosition
             when closest Position find you have to implement the logic for prev and next key...
             */
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val position = params.key?:1
            val response = quoteApi.getQuotes(position)

            return LoadResult.Page(
                data = response.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.totalPages) null else position + 1
            )
        }
        catch (e:Exception){
           LoadResult.Error(e)
        }
    }


}