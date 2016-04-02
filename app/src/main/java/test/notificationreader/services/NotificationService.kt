package test.notificationreader.services

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import test.notificationreader.model.DeviceStatusChecker
import test.notificationreader.model.TextReader
import test.notificationreader.model.cache.Settings
import test.notificationreader.model.notifications.Notification
import test.notificationreader.model.notifications.NotificationHandler

/**
 * Used to listen notifications for SDK >= 18
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
class NotificationService : NotificationListenerService() {
  internal var mNotificationActor: NotificationHandler? = null

  override fun onBind(intent: Intent): IBinder? {
    mNotificationActor = NotificationHandler(Settings(applicationContext),
        TextReader(applicationContext), DeviceStatusChecker(applicationContext))

    return super.onBind(intent)
  }

  override fun onNotificationPosted(sbn: StatusBarNotification) {
    val text: String

    if (sbn.notification.tickerText == null) {
      text = sbn.notification.extras.get("android.text") as String? ?: "Empty"
    } else {
      text = sbn.notification.tickerText.toString()
    }

    val aPackage = sbn.packageName

    val notification = Notification.with(text, aPackage)
    mNotificationActor?.handle(notification)
  }

  override fun onNotificationRemoved(sbn: StatusBarNotification) {
  }
}
