package com.example.charlestest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class TopViewModel(
    private val retrofit: Retrofit
) : ViewModel() {

    private var _product: MutableLiveData<Product> = MutableLiveData()
    val product: MutableLiveData<Product>
        get() = _product

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

    fun dataAcquisitionEvery2Seconds() {
        viewModelScope.launch {
            var i = 1
            while (true) {
                try {
                    val service = retrofit.create(ProductService::class.java)
                    _product.value = service.find(i).also { i++ }
                    if (i > 30) i = 1
                    Log.d("TopViewModel", "$i")
                } catch (e: Exception) {
                    Log.e("TopViewModel", e.toString())
                }
                kotlinx.coroutines.delay(2000)
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