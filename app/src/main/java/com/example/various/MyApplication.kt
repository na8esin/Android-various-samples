package com.example.various

import android.app.Application
import java.io.File

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        val dexOutputDir: File = codeCacheDir
        dexOutputDir.setReadOnly()
    }
}