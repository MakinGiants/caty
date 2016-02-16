package test.notificationreader.model.notifications

class MessageNotification(text: String, aPackage: String?) : Notification(text, aPackage) {
    val name: String
    val message: String

    init {
        val splitedText = text.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        name = splitedText[0].trim { it <= ' ' }
        message = splitedText[1].trim { it <= ' ' }
    }
}
