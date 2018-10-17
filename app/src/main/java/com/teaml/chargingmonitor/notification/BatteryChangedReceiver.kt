package com.teaml.chargingmonitor.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.os.Build
import android.util.Log

class BatteryChangedReceiver : BroadcastReceiver() {


    companion object {
        private val TAG: String = BatteryChangedReceiver::class.java.simpleName
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BATTERY_CHANGED) {
            Log.d(TAG, "onReceive")

            // get battery percent
            val level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            val batteryPercentage = (level / scale.toFloat() * 100).toInt()

            // send data to batteryChangedService
            val serviceIntent = Intent(context, NotificationService::class.java)
            serviceIntent.putExtra(NotificationService.ACTION_START_FOREGROUND_NOTIFICATION, true)
            serviceIntent.putExtra(NotificationService.EXTRA_BATTERY_PERCENTAGE, batteryPercentage)

            // start service
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent)
            } else {
                context.startService(serviceIntent)
            }

        }
    }

}