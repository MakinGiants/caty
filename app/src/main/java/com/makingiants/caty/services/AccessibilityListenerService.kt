package com.makingiants.caty.services

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.view.accessibility.AccessibilityEvent
import com.makingiants.caty.model.DeviceStatusChecker
import com.makingiants.caty.model.TextReader
import com.makingiants.caty.model.cache.Settings
import com.makingiants.caty.model.notifications.Notification
import com.makingiants.caty.model.notifications.NotificationHandler

class AccessibilityListenerService : AccessibilityService() {
  internal var mNotificationActor: NotificationHandler? = null

  public override fun onServiceConnected() {
    super.onServiceConnected()

    mNotificationActor = NotificationHandler(Settings(applicationContext),
        TextReader(applicationContext), DeviceStatusChecker(applicationContext))

    val info = AccessibilityServiceInfo()
    info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED
    info.feedbackType = AccessibilityServiceInfo.FEEDBACK_AUDIBLE
    info.notificationTimeout = 100
    serviceInfo = info
  }

  override fun onAccessibilityEvent(event: AccessibilityEvent) {
    if (event.eventType == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
      val text = event.getEventText()
      val aPackage = event.packageName.toString()
      val title = aPackage.split(".").last() //TODO: get event title

      // TODO: Check when an event should sound or not
      val notification = Notification.with(text, title, aPackage, true)
      mNotificationActor?.handle(notification)
    }
  }

  override fun onInterrupt() {

  }

  fun AccessibilityEvent.getEventText(): String {
    val sb = StringBuilder()
    for (s in text) {
      sb.append(s)
    }
    return sb.toString()
  }

}