package com.example.paging3.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Quote")
data class Result(

    @PrimaryKey(autoGenerate = false)
    val id: String,
      val author: String,
      val authorSlug: String,
      val content: String,
      val dateAdded: String,
      val dateModified: String,
      val length: Int,
      val tags: List<String>)
