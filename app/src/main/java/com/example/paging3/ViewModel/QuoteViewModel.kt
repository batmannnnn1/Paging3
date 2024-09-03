package com.example.paging3.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.paging3.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(val repository: Repository): ViewModel() {

    val list = repository.getQuotes()
        .cachedIn(viewModelScope)

}