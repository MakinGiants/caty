package test.notificationreader.model.notifications

import rx.Observable
import rx.Subscription
import test.notificationreader.model.DeviceStatusChecker
import test.notificationreader.model.TextReader
import test.notificationreader.model.cache.Settings
import java.util.concurrent.TimeUnit

open class NotificationHandler(private val settings: Settings,
                               val textReader: TextReader,
                               val deviceStatusChecker: DeviceStatusChecker) {

  val notifications: MutableList<Notification> = mutableListOf()
  var readSubscription: Subscription? = null

  open fun handle(notification: Notification) {
    var shouldRead = settings.readNotificationEnabled && notification.haveSound

    shouldRead = shouldRead && when (settings.playJustWithHeadphones) {
      true -> deviceStatusChecker.headphonesConnected
      else -> true
    }

    if (shouldRead) {
      readSubscription?.unsubscribe()
      readSubscription = parseWithDelay(notification).subscribe({
        it.forEach { textReader.read("${it.first}: ${it.second}") }
      })
    }
  }

  open fun parseWithDelay(notification: Notification): Observable<List<Pair<String, String>>> {
    notifications.add(notification)

    return Observable.fromCallable { notifications }
        .delay(500, TimeUnit.MILLISECONDS)
        .map { it.groupBy { (it as? MessageNotification)?.name ?: it.appPackage ?: "" } }
        .map {
          it.map {
            val text = it.value.map { (it as? MessageNotification)?.message ?: it.text }.joinToString(" ")
            Pair(it.key, text)
          }
        }
  }

}
