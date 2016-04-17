package com.makingiants.caty.model.notifications

open class Notification(val text: String, val appPackage: String?, val haveSound: Boolean = true) {
  companion object {
    fun with(text: String, appPackage: String, haveSound: Boolean = true): Notification {
      if (text.contains(":")) {
        return MessageNotification(text, appPackage, haveSound)
      } else if (appPackage.contains("android")) {
        return SystemNotification(text, appPackage, haveSound)
      }

      return Notification(text, appPackage, haveSound)
    }
  }
}

class SystemNotification(text: String, appPackage: String?, haveSound: Boolean = true) :
    Notification(text, appPackage, haveSound)

class MessageNotification(text: String, appPackage: String?, haveSound: Boolean) :
    Notification(text, appPackage, haveSound) {

  val name: String
  val message: String

  init {
    val splitText = text.split(":".toRegex())
    name = splitText[0].trim(' ')
    message = splitText[1].trim(' ')
  }
}
