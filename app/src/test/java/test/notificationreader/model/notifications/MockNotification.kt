package test.notificationreader.model.notifications

import test.notificationreader.model.notifications.Notification


object MockNotification {
    fun notification(): Notification = Notification("This is a notification", "com.test.package")
}
