package com.makingiants.caty.model.notifications

open class Notification(var text: String,
                        val title: String,
                        val appPackage: String?,
                        val haveSound: Boolean = true) {
  companion object {
    fun with(text: String, title: String, appPackage: String, haveSound: Boolean = true): Notification {
      if (text.contains(":")) {
        return MessageNotification(text, title, appPackage, haveSound)
      } else {
        return Notification(text, title, appPackage, haveSound)
      }
    }
  }
}

class MessageNotification(text: String, title: String, appPackage: String?, haveSound: Boolean) :
    Notification(text, title, appPackage, haveSound) {

  val user: String

  init {
    val splitText = text.split(":".toRegex())
    user = splitText[0].trim(' ')
    this.text = splitText[1].trim(' ')
  }
}
