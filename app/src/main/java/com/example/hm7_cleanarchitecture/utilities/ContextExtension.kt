package com.example.hm7_cleanarchitecture.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.core.content.getSystemService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart
//https://youtu.be/tSyO64FFkTk?t=1403

@OptIn(ExperimentalCoroutinesApi::class)
val Context.networkChangeFlow: Flow<Boolean>
    get() = callbackFlow {

        val connectManager = getSystemService<ConnectivityManager>()

        checkNotNull(connectManager) {
            "manager is null"
        }
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
                }
            }
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(true)
            }

            override fun onLost(network: Network) {
                trySend(false)
            }
        }

        connectManager.registerNetworkCallback(request, callback)

        awaitClose {
            connectManager.unregisterNetworkCallback(callback)
        }
    }
        .onStart {
        }
