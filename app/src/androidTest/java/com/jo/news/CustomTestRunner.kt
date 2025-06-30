package com.jo.news

import android.app.Application
import android.content.Context
import dagger.hilt.android.testing.HiltTestApplication

class CustomTestRunner : androidx.test.runner.AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}