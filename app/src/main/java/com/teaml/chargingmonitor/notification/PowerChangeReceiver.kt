package com.teaml.chargingmonitor.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import java.lang.Exception

class PowerChangeReceiver : BroadcastReceiver() {


    companion object {
        private val TAG: String = PowerChangeReceiver::class.java.simpleName
        private val batteryChangedReceiver = BatteryChangedReceiver()
    }

    override fun onReceive(context: Context, intent: Intent) {

        when (intent.action) {
            Intent.ACTION_POWER_CONNECTED -> {
                onPowerConnected(context)
            }

            Intent.ACTION_POWER_DISCONNECTED -> {
                onPowerDisconnected(context)
            }
        }

    }

    private fun onPowerConnected(context: Context) {
        Log.d(TAG, "onPowerConnected")

        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        context.applicationContext.registerReceiver(batteryChangedReceiver, intentFilter)
    }

    private fun onPowerDisconnected(context: Context) {
        Log.d(TAG, "onPowerDisconnected")

        try {
            context.applicationContext.unregisterReceiver(batteryChangedReceiver)
        } catch (e: Exception) {
            Log.e(TAG, "onPowerDisconnected", e)
        } finally {
            val serviceIntent = Intent(context, NotificationService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent)
            } else {
                context.startService(serviceIntent)
            }
        }
    }
}