package com.example.charlestest

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class TopViewModel(
    private val retrofit: Retrofit
) : ViewModel() {

    fun firstProduct() {
        viewModelScope.launch {
            try {
                val product = retrofit.create(ProductService::class.java).first()
                Log.d("TopViewModel", product.toString())
            } catch (e: Exception) {
                Log.e("TopViewModel", e.toString())
            }
        }
    }
}

class TopViewModelFactory(private val retrofit: Retrofit) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TopViewModel(retrofit) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}