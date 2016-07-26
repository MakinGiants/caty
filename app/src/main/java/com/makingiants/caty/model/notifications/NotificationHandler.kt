package com.makingiants.caty.model.notifications

import com.makingiants.caty.model.DeviceStatusChecker
import com.makingiants.caty.model.TextReader
import com.makingiants.caty.model.cache.Settings
import rx.Observable
import rx.Subscription
import java.util.concurrent.TimeUnit

open class NotificationHandler(val settings: Settings,
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

    shouldRead = shouldRead && when (settings.readJustMessages) {
      true -> notification is MessageNotification
      else -> true
    }

    if (shouldRead) {
      //TODO: Enqueue notifications so read is not paused by others
      readSubscription?.unsubscribe()
      readSubscription = parseWithDelay(notification).subscribe({
        it.forEach { textReader.read("${it.first}. ${it.second}") }
      })
    }
  }

  open fun parseWithDelay(notification: Notification): Observable<List<Pair<String, String>>> {
    notifications.add(notification)

    return Observable.fromCallable { notifications }
        .delay(500, TimeUnit.MILLISECONDS)
        .map { it.groupBy { (it as? MessageNotification)?.user ?: it.title } }
        .map { it.map { Pair(it.key, it.value.map { it.text }.joinToString(" ")) } }
        .doOnEach { notifications.clear() }
  }

}
