package com.teaml.chargingmonitor.notification

import android.app.IntentService
import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.teaml.chargingmonitor.R
import com.teaml.chargingmonitor.utils.AppConstant.CHANNEL_ID
import com.teaml.chargingmonitor.utils.AppConstant.CHANNEL_NAME
import com.teaml.chargingmonitor.utils.Common

class NotificationService : IntentService("batterChanged") {


    companion object {
        private val TAG: String = NotificationService::class.java.simpleName

        val ACTION_START_FOREGROUND_NOTIFICATION = "com.teaml.chargingmonitor.start_foreground_notification"
        val EXTRA_BATTERY_PERCENTAGE = "com.teaml.chargingmonitor.battery_percentage"

        private const val NOTIFICATION_ID = 1
        private const val FOREGROUND_ID = 2


    }
    private lateinit var notificationBuilder: NotificationCompat.Builder

    override fun onCreate() {
        super.onCreate()

        notificationBuilder =  NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .setOnlyAlertOnce(true)
                .setAutoCancel(false)
                .setOngoing(true)
                .setContentTitle("Charging...")
                .setSmallIcon(R.drawable.ic_charging)

    }

    override fun onHandleIntent(intent: Intent) {
        Log.d(TAG, "onHandleIntent")

        // get extra from intent
        val startForeground = intent.getBooleanExtra(ACTION_START_FOREGROUND_NOTIFICATION, false)
        val batterPercentage = intent.getIntExtra(EXTRA_BATTERY_PERCENTAGE, -1)


        val notificationManager = Common.getNotificationManager(applicationContext,
                CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)

        // && batterPercentage > 100 to enable device to turn on green led when full charge
        if (startForeground && batterPercentage < 100) {

            val notification = notificationBuilder
                    .setContentText("Battery Level: $batterPercentage ")
                    .setLights(getLightsColor(applicationContext, batterPercentage), 5000 * 60 * 60 , 1000)
                    .build()

            startForeground(FOREGROUND_ID, notification)
            notificationManager.notify(NOTIFICATION_ID, notification)

        } else {
            stopForeground(true)
            notificationManager.cancel(NOTIFICATION_ID)
        }
    }


    private fun getLightsColor(context: Context, batterPercent: Int) =
            when (batterPercent) {
                in 90..99 -> ContextCompat.getColor(context, R.color.color90_100)
                in 80 ..89 -> ContextCompat.getColor(context, R.color.color80_89)
                in 70..79 -> ContextCompat.getColor(context, R.color.color70_79)
                in 60..69 -> ContextCompat.getColor(context, R.color.color60_69)
                in 50..59 -> ContextCompat.getColor(context, R.color.color60_69)
                in 30..49 -> ContextCompat.getColor(context, R.color.color30_49)
                else  -> ContextCompat.getColor(context, R.color.color0_29)
            }


}