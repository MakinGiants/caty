package test.notificationreader.model.notifications

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import test.notificationreader.model.notifications.MessageNotification
import test.notificationreader.model.notifications.Notification
import test.notificationreader.model.notifications.SystemNotification

class NotificationTest {
    @Test
    fun buildMessageNotification() {
        val notification = Notification.with("Maria: hello Daniel", "facebook.messenger")

        assertThat(notification).isInstanceOf(MessageNotification::class.java)

        val messageNotification = notification as MessageNotification
        assertThat(messageNotification.name).isEqualTo("Maria")
        assertThat(messageNotification.message).isEqualTo("hello Daniel")
    }

    @Test
    fun buildSystemNotification() {
        val notification = Notification.with("Something happened with phone", "android.vending")
        assertThat(notification).isInstanceOf(SystemNotification::class.java)
    }

    @Test
    fun buildOtherNotification() {
        val notification = Notification.with("Maria hello Daniel", "facebook.messenger")

        assertThat(notification).isInstanceOf(Notification::class.java)
        assertThat(notification).isNotInstanceOf(MessageNotification::class.java)
        assertThat(notification).isNotInstanceOf(SystemNotification::class.java)
    }
}
