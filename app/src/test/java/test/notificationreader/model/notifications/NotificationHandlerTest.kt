package test.notificationreader.model.notifications

import net.paslavsky.kotlin.mockito.verifyNoMoreInteractions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import test.notificationreader.model.DeviceStatusChecker
import test.notificationreader.model.TextReader
import test.notificationreader.model.cache.Settings

class NotificationHandlerTest {
  @Mock lateinit var mockedTextReader: TextReader
  @Mock lateinit var mockedSettings: Settings
  @Mock lateinit var mockedDeviceStatusChecker: DeviceStatusChecker
  lateinit var notificationHandler: NotificationHandler

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
    notificationHandler = NotificationHandler(mockedSettings, mockedTextReader, mockedDeviceStatusChecker)

    Mockito.`when`(mockedSettings.readNotificationEnabled).thenReturn(true)
  }

  @Test
  fun test_handle_checkJustWithHeadphonesSettings_ifPlayJustWithHeadphones_ifNoHeadphones_dontRead() {
    val notification = MockNotification.notification()

    Mockito.`when`(mockedSettings.playJustWithHeadphones).thenReturn(true)
    Mockito.`when`(mockedDeviceStatusChecker.headphonesConnected).thenReturn(false)

    notificationHandler.handle(notification)

    verify(mockedTextReader, times(0)).read(notification.text)
  }

  @Test
  fun test_handle_checkJustWithHeadphonesSettings_ifPlayJustWithHeadphones_ifHeadphones_read() {
    val notification = MockNotification.notification()

    Mockito.`when`(mockedSettings.playJustWithHeadphones).thenReturn(true)
    Mockito.`when`(mockedDeviceStatusChecker.headphonesConnected).thenReturn(true)

    notificationHandler.handle(notification)

    verify(mockedTextReader).read(notification.text)
  }

  @Test
  fun test_handle_checkJustWithHeadphonesSettings_ifDontPlayJustWithHeadphones_readAlways() {
    val notification = MockNotification.notification()

    Mockito.`when`(mockedSettings.playJustWithHeadphones).thenReturn(false)

    notificationHandler.handle(notification)

    verify(mockedTextReader).read(notification.text)
  }

  @Test
  fun test_handle_checkReadNotifications_ifFalse_DoNothing() {
    val notification = MockNotification.notification()

    Mockito.`when`(mockedSettings.readNotificationEnabled).thenReturn(false)

    notificationHandler.handle(notification)

    verify(mockedTextReader, times(0)).read(notification.text)
  }

  @Test
  fun test_handle_ifNotificationDontHaveSound_doNothing() {
    val notification = MockNotification.notification(haveSound = false)

    Mockito.`when`(mockedSettings.readNotificationEnabled).thenReturn(true)

    notificationHandler.handle(notification)

    verifyNoMoreInteractions(mockedTextReader)
  }

  @Test
  fun test_handle_checkReadNotifications_ifTrue_work() {
    val notification = MockNotification.notification()

    Mockito.`when`(mockedSettings.readNotificationEnabled).thenReturn(true)

    notificationHandler.handle(notification)

    verify(mockedTextReader).read(notification.text)
  }

  @Test
  fun test_handle_multipleMessagesFaster_joinMessages() {
    val notification = MockNotification.messageNotification("James", "text 1")
    val notification2 = MockNotification.messageNotification("James", "text 2")

    Mockito.`when`(mockedSettings.readNotificationEnabled).thenReturn(true)

    notificationHandler.parseWithDelay(notification)
    val pair = notificationHandler.parseWithDelay(notification2).toBlocking().first().first()

    assertThat(pair.first).isEqualTo("James")
    assertThat(pair.second).isEqualTo("text 1 text 2")
  }
}