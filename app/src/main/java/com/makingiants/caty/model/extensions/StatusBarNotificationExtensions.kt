package com.makingiants.caty.model.extensions

import android.service.notification.StatusBarNotification

/**
 * Text inside the notification
 */
val StatusBarNotification.text: String
  get() {
    val text = notification.extras.get("android.text") as String?
    return text ?: notification.tickerText?.toString() ?: "Empty"
  }

val StatusBarNotification.title: String
  get() = notification.extras.get("android.title") as String? ?: "Empty"

