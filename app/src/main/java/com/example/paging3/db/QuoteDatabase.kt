package com.example.paging3.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuoteDao::class,RemoteKeysDao::class], version = 1)
abstract class QuoteDatabase: RoomDatabase() {

    abstract fun getQuoteDao():QuoteDao
    abstract fun getRemoteKey():RemoteKeysDao

}