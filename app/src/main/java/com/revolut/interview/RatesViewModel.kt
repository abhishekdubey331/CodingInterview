package com.revolut.interview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RatesViewModel() : ViewModel() {
}
object ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return RatesViewModel() as T
    }
}