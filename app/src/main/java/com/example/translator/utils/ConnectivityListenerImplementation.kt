package com.example.translator.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.translator.App
import com.example.translator.data.connectivitylistener.ConnectivityListener

class ConnectivityListenerImplementation : ConnectivityListener {

    override fun isOnline(): Boolean {
        val connectivityManager =
            App.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            val activeNetwork = it.activeNetwork
            val capabilities = it.getNetworkCapabilities(activeNetwork)
            return capabilities != null &&
                    (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
        }
        return false
    }
}