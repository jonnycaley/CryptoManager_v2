package com.example.cryptomanager_v2.ui.home.ui.portfolio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class PortfolioViewModel @Inject constructor(
//    fiatsDao: DBFiatsDao
) : ViewModel() {

    init {
//        fiatsDao.getAll().observeForever {
//            _text.value = it.toString()
//        }
    }
    private val _text = MutableLiveData<String>()
    val text: LiveData<String> = _text
}