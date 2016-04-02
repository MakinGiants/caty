package test.notificationreader.services

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.view.accessibility.AccessibilityEvent
import test.notificationreader.model.DeviceStatusChecker
import test.notificationreader.model.TextReader
import test.notificationreader.model.cache.Settings
import test.notificationreader.model.notifications.Notification
import test.notificationreader.model.notifications.NotificationHandler

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
    val text = getEventText(event)
    val aPackage = event.packageName.toString()

    val notification = Notification.with(text, aPackage)
    mNotificationActor?.handle(notification)
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