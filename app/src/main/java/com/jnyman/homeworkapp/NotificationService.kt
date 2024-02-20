package com.jnyman.homeworkapp

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class NotificationService(
    private val context: Context
) {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // Create an explicit intent for an Activity in your app.
    private val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    private val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    fun sendNotification() {
        var notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.skull)
            .setContentTitle("It's dark")
            .setContentText("The light level detected by device is low.")
            .setStyle(
                NotificationCompat.BigTextStyle()
                .bigText("The light level detected by device is low. Press notification to open app."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()



        notificationManager.notify(0, notification)
    }

    companion object {
        const val CHANNEL_ID = "channel_1"
    }
}