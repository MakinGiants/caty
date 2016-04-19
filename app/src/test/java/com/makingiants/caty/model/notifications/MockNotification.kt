package com.makingiants.caty.model.notifications


object MockNotification {
  fun notification(haveSound: Boolean = true): Notification =
      Notification("This is a notification", "title", "com.test.package", haveSound)

  fun messageNotification(name: String, message: String): Notification =
      MessageNotification("$name:$message", "title", "com.test.package", true)
}
