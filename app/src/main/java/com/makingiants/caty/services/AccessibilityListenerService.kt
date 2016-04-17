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
      val text = getEventText(event)
      val aPackage = event.packageName.toString()

      // TODO: Check when an event should sound or not
      val notification = Notification.with(text, aPackage)
      mNotificationActor?.handle(notification)
    }
  }

  override fun onInterrupt() {

  }

  companion object {
    fun getEventText(event: AccessibilityEvent): String {
      val sb = StringBuilder()
      for (s in event.text) {
        sb.append(s)
      }
      return sb.toString()
    }
  }
}