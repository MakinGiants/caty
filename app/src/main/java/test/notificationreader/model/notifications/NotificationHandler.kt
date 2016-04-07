package test.notificationreader.model.notifications

import test.notificationreader.model.DeviceStatusChecker
import test.notificationreader.model.TextReader
import test.notificationreader.model.cache.Settings

open class NotificationHandler(private val settings: Settings,
                               val textReader: TextReader,
                               val deviceStatusChecker: DeviceStatusChecker) {

    open fun handle(notification: Notification) {
        var shouldRead = settings.readNotificationEnabled && notification.haveSound

        shouldRead = shouldRead && when (settings.playJustWithHeadphones) {
            true -> deviceStatusChecker.headphonesConnected
            else -> true
        }

        if (shouldRead) {
            textReader.read(notification.text)
        }
    }

}
