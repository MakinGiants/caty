package com.makingiants.caty.services

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.makingiants.caty.CatyApplication
import com.makingiants.caty.model.extensions.text
import com.makingiants.caty.model.extensions.title
import com.makingiants.caty.model.notifications.Notification
import com.makingiants.caty.model.notifications.NotificationHandler
import javax.inject.Inject

/**
 * Used to listen notifications for SDK >= 18
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
class NotificationService : NotificationListenerService() {
  @Inject lateinit var mNotificationActor: NotificationHandler

  override fun onBind(intent: Intent): IBinder? {
    (application as CatyApplication).applicationComponent.inject(this)

    return super.onBind(intent)
  }

  override fun onNotificationPosted(sbn: StatusBarNotification) {
    val notification = Notification.with(sbn.text, sbn.title, sbn.packageName,
        sbn.notification.sound != null)
    mNotificationActor.handle(notification)
  }

  override fun onNotificationRemoved(sbn: StatusBarNotification) {
  }
}
