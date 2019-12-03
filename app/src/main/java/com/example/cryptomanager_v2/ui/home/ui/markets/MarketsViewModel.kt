package com.example.cryptomanager_v2.ui.home.ui.markets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MarketsViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is markets Fragment"
    }
    val text: LiveData<String> = _text
}