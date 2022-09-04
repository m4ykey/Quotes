package com.example.quotes.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotes.model.Quotes
import com.example.quotes.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val repository: QuoteRepository
) : ViewModel(){

    private var quoteLiveData = MutableLiveData<List<Quotes>>()

    init {
        getQuotes()
    }

    private fun getQuotes() = viewModelScope.launch {
        repository.getQuotes().let { response ->
            if (response.isSuccessful){
                quoteLiveData.value = response.body()
            }
        }
    }

    fun observeQuotesLiveData() : LiveData<List<Quotes>>{
        return quoteLiveData
    }
}