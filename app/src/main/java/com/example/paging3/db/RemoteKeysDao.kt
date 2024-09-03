package com.example.paging3.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.paging3.Model.QuoteRemoteKeys

@Dao
interface RemoteKeysDao {

    @Query("SELECT * FROM QuoteRemoteKeys WHERE id =:id")
 suspend fun getRemoteKey(id:String):QuoteRemoteKeys


 @Insert(onConflict = OnConflictStrategy.REPLACE)
 suspend fun addRemoteKey(remoteKeys: List<QuoteRemoteKeys>)


 @Query("DELETE FROM quoteremotekeys")
 suspend fun deleteRemoteKey()

}