package test.notificationreader.services

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.view.accessibility.AccessibilityEvent
import test.notificationreader.model.TextReader
import test.notificationreader.model.notifications.NotificationActor
import test.notificationreader.model.notifications.NotificationFactory

class AccessibilityListenerService : AccessibilityService() {
    internal var mNotificationActor: NotificationActor? = null

    public override fun onServiceConnected() {
        super.onServiceConnected()
        mNotificationActor = NotificationActor(TextReader(applicationContext))

        val info = AccessibilityServiceInfo()
        info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_AUDIBLE
        info.notificationTimeout = 100
        serviceInfo = info
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        val text = getEventText(event)
        val aPackage = event.packageName.toString()

        val notification = NotificationFactory.build(text, aPackage)
        mNotificationActor?.manageNotification(notification)
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