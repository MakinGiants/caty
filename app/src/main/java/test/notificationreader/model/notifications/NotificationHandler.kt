package test.notificationreader.model.notifications

import test.notificationreader.model.DeviceStatusChecker
import test.notificationreader.model.TextReader
import test.notificationreader.model.cache.Settings

class NotificationHandler(private val settings: Settings,
                          val textReader: TextReader,
                          val deviceStatusChecker: DeviceStatusChecker) {

    open fun handle(notification: Notification) {
        val shouldRead = when (settings.playJustWithHeadphones) {
            true -> deviceStatusChecker.headphonesConnected
            else -> true
        }

        if (shouldRead) {
            textReader.read(notification.text)
        }
    }
    
}
