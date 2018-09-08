package com.example.prasetyo.footballmatchschedule.app

import android.app.Application
import android.content.res.Configuration
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        // Required initialization logic here!

        AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY)


        val okHttpClient = OkHttpClient().newBuilder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()
        AndroidNetworking.initialize(applicationContext, okHttpClient)
    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    override fun onLowMemory() {
        super.onLowMemory()
    }

}