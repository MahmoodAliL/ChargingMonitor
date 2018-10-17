package com.teaml.chargingmonitor.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.content.getSystemService

object Common {

    fun getNotificationManager(
            context: Context,
            channelId: String,
            channelName: String,
            channelImportant: Int
    ): NotificationManager {

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(channelId, channelName, channelImportant)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        return notificationManager
    }
}