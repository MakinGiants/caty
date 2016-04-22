package com.makingiants.caty.model.notifications

import com.makingiants.caty.model.DeviceStatusChecker
import com.makingiants.caty.model.TextReader
import com.makingiants.caty.model.cache.Settings
import com.makingiants.caty.model.notifications.MockNotification.messageNotification
import com.makingiants.caty.model.notifications.MockNotification.notification
import net.paslavsky.kotlin.mockito.spy
import net.paslavsky.kotlin.mockito.verifyNoMoreInteractions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

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
    Mockito.`when`(mockedSettings.readJustMessages).thenReturn(false)
  }

  @Test
  fun test_handle_checkJustWithHeadphonesSettings_ifPlayJustWithHeadphones_ifNoHeadphones_dontRead() {
    val notification = notification()

    Mockito.`when`(mockedSettings.playJustWithHeadphones).thenReturn(true)
    Mockito.`when`(mockedDeviceStatusChecker.headphonesConnected).thenReturn(false)

    notificationHandler.handle(notification)

    verify(mockedTextReader, times(0)).read(notification.text)
  }

  @Test
  fun test_handle_checkJustWithHeadphonesSettings_ifPlayJustWithHeadphones_ifHeadphones_read() {
    val notification = notification()
    val spiedNotificationHandler = spy(notificationHandler)

    Mockito.`when`(mockedSettings.playJustWithHeadphones).thenReturn(true)
    Mockito.`when`(mockedDeviceStatusChecker.headphonesConnected).thenReturn(true)

    spiedNotificationHandler.handle(notification)

    verify(spiedNotificationHandler).parseWithDelay(notification)
  }

  @Test
  fun test_handle_checkJustWithHeadphonesSettings_ifDontPlayJustWithHeadphones_readAlways() {
    val notification = notification()
    val spiedNotificationHandler = spy(notificationHandler)

    Mockito.`when`(mockedSettings.playJustWithHeadphones).thenReturn(false)

    spiedNotificationHandler.handle(notification)

    verify(spiedNotificationHandler).parseWithDelay(notification)
  }

  @Test
  fun test_handle_checkReadNotifications_ifFalse_DoNothing() {
    val notification = notification()

    Mockito.`when`(mockedSettings.readNotificationEnabled).thenReturn(false)

    notificationHandler.handle(notification)

    verify(mockedTextReader, times(0)).read(notification.text)
  }

  @Test
  fun test_handle_ifNotificationDontHaveSound_doNothing() {
    val notification = notification(haveSound = false)

    Mockito.`when`(mockedSettings.readNotificationEnabled).thenReturn(true)

    notificationHandler.handle(notification)

    verifyNoMoreInteractions(mockedTextReader)
  }

  @Test
  fun test_handle_checkReadNotifications_ifTrue_work() {
    val notification = notification()
    val spiedNotificationHandler = spy(notificationHandler)

    Mockito.`when`(mockedSettings.readNotificationEnabled).thenReturn(true)

    spiedNotificationHandler.handle(notification)

    verify(spiedNotificationHandler).parseWithDelay(notification)
  }

  @Test
  fun test_handle_multipleMessagesFaster_joinMessages() {
    val notification = messageNotification("James", "text 1")
    val notification2 = messageNotification("James", "text 2")

    Mockito.`when`(mockedSettings.readNotificationEnabled).thenReturn(true)

    notificationHandler.parseWithDelay(notification)
    val pair = notificationHandler.parseWithDelay(notification2).toBlocking().first().first()

    assertThat(pair.first).isEqualTo("James")
    assertThat(pair.second).isEqualTo("text 1 text 2")
  }

  fun test_handle_check(readJustMessages: Boolean, notification: Notification) {
    val spiedNotificationHandler = spy(notificationHandler)

    val notification = messageNotification("James", "text 1")

    Mockito.`when`(mockedSettings.readJustMessages).thenReturn(true)

    spiedNotificationHandler.parseWithDelay(notification)

    verify(spiedNotificationHandler).parseWithDelay(notification)
  }

  @Test
  fun test_handle_checkReadJustMessage_ifIsMessage_work() {
    val spiedNotificationHandler = spy(notificationHandler)

    val notification = messageNotification("James", "text 1")

    Mockito.`when`(mockedSettings.readJustMessages).thenReturn(true)

    spiedNotificationHandler.parseWithDelay(notification)

    verify(spiedNotificationHandler).parseWithDelay(notification)
  }

  @Test
  fun test_handle_checkReadJustMessage_ifIsNotAMessage_doNothing() {
    val spiedNotificationHandler = spy(notificationHandler)
    val notification = notification(true)

    Mockito.`when`(mockedSettings.readJustMessages).thenReturn(true)

    spiedNotificationHandler.handle(notification)

    verify(spiedNotificationHandler, times(0)).parseWithDelay(notification)
  }
}