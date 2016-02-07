package test.notificationreader.model.notifications

import test.notificationreader.model.TextReader

class NotificationActor(private val textReader: TextReader) {
    fun manageNotification(notification: Notification) {
        textReader.read(notification.text)
    }
}
