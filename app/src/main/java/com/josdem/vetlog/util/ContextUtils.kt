package com.josdem.vetlog.util

import android.content.Context
import android.net.ConnectivityManager

class ContextUtils(
    val context: Context,
) {
    fun getSystemService(): ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}
