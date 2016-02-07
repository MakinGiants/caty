package test.notificationreader.model.notifications

object NotificationFactory {
    fun build(text: String, aPackage: String): Notification {
        if (text.contains(":")) {
            return MessageNotification(text, aPackage)
        } else if (aPackage.contains("android")) {
            return SystemNotification(text, aPackage)
        }

        return Notification(text, aPackage)
    }
}
