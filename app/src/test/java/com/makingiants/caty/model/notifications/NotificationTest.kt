package com.makingiants.caty.model.notifications

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class NotificationTest {
  @Test
  fun textWithDoublePoints_buildMessageNotification() {
    val notification = Notification.with("Maria: hello Daniel", "Facebook", "facebook.messenger")

    assertThat(notification).isInstanceOf(MessageNotification::class.java)

    val messageNotification = notification as MessageNotification
    assertThat(messageNotification.user).isEqualTo("Maria")
    assertThat(messageNotification.text).isEqualTo("hello Daniel")
  }

  @Test
  fun textWithoutDoublePoints_buildOtherNotification() {
    val notification = Notification.with("Maria hello Daniel", "Facebook", "facebook.messenger")

    assertThat(notification).isInstanceOf(Notification::class.java)
    assertThat(notification).isNotInstanceOf(MessageNotification::class.java)
  }

  @Test
  fun messageNotification_textReturnMessage() {
    val notification = MockNotification.messageNotification("Maria", "hello Daniel")
    assertThat(notification.text).isEqualTo("hello Daniel")
  }
}
