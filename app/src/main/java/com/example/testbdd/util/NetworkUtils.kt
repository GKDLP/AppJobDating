package com.example.testbdd.util

import android.content.Context
import android.widget.Toast
import com.example.testbdd.R
import okhttp3.OkHttpClient
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

object NetworkUtils {
    private const val TIMEOUT_SECONDS = 30L

    val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()

    fun handleError(context: Context, throwable: Throwable) {
        val message = when (throwable) {
            is SocketTimeoutException -> context.getString(R.string.error_timeout)
            is UnknownHostException -> context.getString(R.string.error_network)
            is HttpException -> context.getString(R.string.error_generic, throwable.message())
            else -> context.getString(R.string.error_generic, throwable.message ?: "Erreur inconnue")
        }
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun validateId(id: Int): Boolean {
        return id > 0
    }
} 