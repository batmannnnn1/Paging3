package com.example.paging3.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.paging3.db.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {


    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context:Context):QuoteDatabase{
        return Room.databaseBuilder(context,QuoteDatabase::class.java,"quoteDB").build()
    }

}