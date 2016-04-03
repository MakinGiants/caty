package test.notificationreader.model.notifications


object MockNotification {
  fun notification(haveSound: Boolean = true): Notification =
      Notification("This is a notification", "com.test.package", haveSound)
}
