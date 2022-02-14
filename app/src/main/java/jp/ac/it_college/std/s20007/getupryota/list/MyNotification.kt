package jp.ac.it_college.std.s20007.getupryota.list

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import jp.ac.it_college.std.s20007.getupryota.R

class MyNotification {
    companion object{
        private const val NOTIFICATION_CHANNEL_ID = "com.example.sample_alarm_manager_channel_id"
        private const val NOTIFICATION_CHANNEL_NAME = "sample_alarm_manager_channel"
        private const val NOTIFICATION_CHANNEL_DESCRIPTION = "This is the sample notification!"
        private const val NOTIFICATION_TITLE = "sample alarm manager"
        private const val NOTIFICATION_MESSAGE = "test message."

        fun sendNotification(context: Context) {
            val channelId = NOTIFICATION_CHANNEL_ID
            val channelName = NOTIFICATION_CHANNEL_NAME
            val channelDescription = NOTIFICATION_CHANNEL_DESCRIPTION

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(channelId, channelName, importance).apply {
                    description = channelDescription
                }
                val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.createNotificationChannel(channel)
            }

            val builder = NotificationCompat.Builder(context, channelId).apply {
                setSmallIcon(R.drawable.ic_baseline_add_circle_outline_24)
                setContentTitle(NOTIFICATION_TITLE)
                setContentText(NOTIFICATION_MESSAGE)
                priority=NotificationCompat.PRIORITY_MAX
            }
            val id = 0
            NotificationManagerCompat.from(context).notify(id,builder.build())
        }
    }
}