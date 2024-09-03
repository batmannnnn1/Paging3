package com.example.paging3.Paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.paging3.Model.QuoteRemoteKeys
import com.example.paging3.Model.Result
import com.example.paging3.Retrofit.QuoteApi
import com.example.paging3.db.QuoteDatabase

@OptIn(ExperimentalPagingApi::class)
class QuoteRemoteMediator(private val quoteApi:QuoteApi
, private val quoteDatabase: QuoteDatabase): RemoteMediator<Int, Result>() {

    val quoteDao = quoteDatabase.getQuoteDao()
    val quoteRemoteDao = quoteDatabase.getRemoteKey()

    @ExperimentalPagingApi
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Result>): MediatorResult {

        return try {
            val currentPage = when (loadType) {

                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)

                    nextPage
                }
            }

            val response = quoteApi.getQuotes(currentPage)
            val endOfPagination = currentPage == response.totalPages

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPagination) null else currentPage + 1



            quoteDatabase.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    quoteDao.deleteQuote()
                    quoteRemoteDao.deleteRemoteKey()
                }


                quoteDao.addQuote(response.results)

                val keys = response.results.map {
                    QuoteRemoteKeys(
                        id = it.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }

                quoteRemoteDao.addRemoteKey(keys)

            }

            MediatorResult.Success(endOfPagination)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Result>
    ): QuoteRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { quoteRemoteDao.getRemoteKey(id = it.id) }
    }


    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Result>): QuoteRemoteKeys? {

        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { quoteRemoteDao.getRemoteKey(id = it.id) }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Result>
    ): QuoteRemoteKeys? {

        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.id?.let {
                quoteRemoteDao.getRemoteKey(id = it)
            }
        }

    }

}
    /*

    getRemoteKeyForFirstItem -> check if state have pages than return the first page..
    then check the first page's data is not empty then give the first page first item...
    if first item is not empty give them their remote key according to their id...

    Also in When block -> give first Page otherwise return the MediaResult.Success()

     endOfPaginationReached = remoteKeys != null:



With remoteKeys Available but prevPage Absent:

remoteKeys != null: true (indicating there are RemoteKeys).
remoteKeys?.prevPage == null: Indicates no previous page to load.
The fallback MediatorResult.Success(endOfPaginationReached = remoteKeys != null) results in endOfPaginationReached = true.
Interpretation: Even if there are RemoteKeys, the lack of a prevPage implies no more data to load, hence pagination stops.




If remoteKeys is not null (remoteKeys != null evaluates to true):

This means some remote keys information is present.
However, the use of this logic here might indicate that we are at a boundary (like the start of pagination) or that the presence of remoteKeys with no prevPage indicates no more pages in that direction.
In this code, endOfPaginationReached is true, indicating no more pages to load, because the presence of remoteKeys confirms we've reached a logical end.


When remoteKeys is not null, it does not mean there's a next page; rather, it means we have information about the pagination state.
The combination of remoteKeys being not null and prevPage being null suggests that there are no more pages in that direction, leading to the conclusion that pagination has reached an end point.
 Thus, endOfPaginationReached is set to true to prevent further attempts to fetch non-existent pages.

     */


