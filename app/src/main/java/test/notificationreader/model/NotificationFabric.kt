package test.notificationreader.model

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.media.RingtoneManager
import android.support.v7.app.NotificationCompat
import test.notificationreader.R
import java.lang.ref.WeakReference

interface NotificationFabric {
    fun notify(title: String, text: String)
}

class AndroidNotificationFabric(context: Context) : NotificationFabric {
    val weakContext = WeakReference(context)

    override fun notify(title: String, text: String) {
        weakContext.get()?.let {
            val pendingIntent = PendingIntent.getActivity(it, 1, Intent(), PendingIntent.FLAG_UPDATE_CURRENT)
            val mBuilder = NotificationCompat.Builder(it)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentIntent(pendingIntent)

            val mNotifyMgr = it.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            mNotifyMgr.notify(1, mBuilder.build());
        }
    }
}
