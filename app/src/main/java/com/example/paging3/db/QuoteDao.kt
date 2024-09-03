package com.example.paging3.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paging3.Model.Result
import com.example.paging3.Paging.PagingSource

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun addQuote(quote:List<Result>)

   @Query("SELECT * FROM Quote")
    fun getQuote():androidx.paging.PagingSource<Int,Result>

   @Query("DELETE FROM Quote")
   suspend fun deleteQuote()
}