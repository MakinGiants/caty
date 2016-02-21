package test.notificationreader.model.notifications

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import test.notificationreader.model.TextReader

class NotificationFactoryTests {
    @Mock internal var mTextReader: TextReader? = null
    internal var mActor: NotificationActor? = null

    @Before
    fun setupMocks() {
        MockitoAnnotations.initMocks(this)
        mActor = NotificationActor(mTextReader!!)
    }

    @Test
    fun buildMessageNotification() {
        val notification = NotificationFactory.build("Maria: hello Daniel", "facebook.messenger")

        assertThat(notification).isInstanceOf(MessageNotification::class.java)

        val messageNotification = notification as MessageNotification
        assertThat(messageNotification.name).isEqualTo("Maria")
        assertThat(messageNotification.message).isEqualTo("hello Daniel")
    }

    @Test
    fun buildSystemNotification() {
        val notification = NotificationFactory.build("Something happened with phone", "android.vending")
        assertThat(notification).isInstanceOf(SystemNotification::class.java)
    }

    @Test
    fun buildOtherNotification() {
        val notification = NotificationFactory.build("Maria hello Daniel", "facebook.messenger")

        assertThat(notification).isInstanceOf(Notification::class.java)
        assertThat(notification).isNotInstanceOf(MessageNotification::class.java)
        assertThat(notification).isNotInstanceOf(SystemNotification::class.java)
    }
}