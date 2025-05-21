package com.example.testbdd.api

import android.util.Log
import mu.KotlinLogging
import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val TAG = "ApiClient"
    private const val BASE_URL = "https://mrzcncacimdmgtveplsk.supabase.co/"
    private const val SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im1yemNuY2FjaW1kbWd0dmVwbHNrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDczOTQ1MzEsImV4cCI6MjA2Mjk3MDUzMX0.LeFfGLKZPC5V1RWISYfOyVnuLiQf0s4eDzQ4EKydavA"
    private val logger = KotlinLogging.logger {}

    //private val loggingInterceptor = HttpLoggingInterceptor().apply {
    //    level = HttpLoggingInterceptor.Level.BODY
    //}

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("apikey", SUPABASE_KEY)
                .addHeader("Authorization", "Bearer $SUPABASE_KEY")
                .addHeader("Content-Type", "application/json")
                .addHeader("Prefer", "return=minimal")
                .build()
            chain.proceed(request)
        }
    //    .addInterceptor(loggingInterceptor)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}