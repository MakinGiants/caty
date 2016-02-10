package test.notificationreader.model

import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.support.v7.app.NotificationCompat
import test.notificationreader.R
import java.lang.ref.WeakReference

interface NotificationFabric {
    fun notify(title: String, text: String)
}

class AndroidNotificationFabric(context: Context) : NotificationFabric {
    val weakContext: WeakReference<Context>

    init {
        weakContext = WeakReference(context)
    }

    override fun notify(title: String, text: String) {
        val context: Context? = weakContext.get()
        if (context != null) {
            val mBuilder = NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(title)
                    .setContentText(text)

            val mNotifyMgr = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            mNotifyMgr.notify(1, mBuilder.build());
        }
    }
}
