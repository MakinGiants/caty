package test.notificationreader.model.notifications

class SystemNotification(text: String, aPackage: String?) : Notification(text, aPackage)

open class Notification(val text: String, val appPackage: String?) {
    companion object {
        fun with(text: String, appPackage: String): Notification {
            if (text.contains(":")) {
                return MessageNotification(text, appPackage)
            } else if (appPackage.contains("android")) {
                return SystemNotification(text, appPackage)
            }

            return Notification(text, appPackage)
        }
    }
}

class MessageNotification(text: String, appPackage: String?) : Notification(text, appPackage) {
    val name: String
    val message: String

    init {
        val splitText = text.split(":".toRegex())
        name = splitText[0].trim(' ')
        message = splitText[1].trim(' ')
    }
}
