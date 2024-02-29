package com.example.various.pager

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OldContentViewModel : ViewModel() {

    private var countDownTimerScope: Job? = null

    fun startCountDownTimer() {
        countDownTimerScope = viewModelScope.launch {
            var i = 1
            while (true) {
                try {
                    Log.d(TAG, "$i")
                    i++
                } catch (e: Exception) {
                    Log.e(TAG, e.toString())
                }
                kotlinx.coroutines.delay(2000)
            }
        }
    }

    fun cancelCountDownTimer() {
        countDownTimerScope?.cancel()
        Log.d(TAG, "cancelCountDownTimer")
    }

    companion object {
        private const val TAG = "OldContentViewModel"
    }
}