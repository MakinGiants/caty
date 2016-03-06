package test.notificationreader.model.notifications.model.notifications

import net.paslavsky.kotlin.mockito.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import test.notificationreader.model.DeviceStatusChecker
import test.notificationreader.model.TextReader
import test.notificationreader.model.cache.Settings
import test.notificationreader.model.notifications.NotificationHandler

class NotificationHandlerTest {
    @Mock lateinit var mockedTextReader: TextReader
    @Mock lateinit var mockedSettings: Settings
    @Mock lateinit var mockedDeviceStatusChecker: DeviceStatusChecker
    lateinit var notificationHandler: NotificationHandler

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        notificationHandler = NotificationHandler(mockedSettings, mockedTextReader, mockedDeviceStatusChecker)
    }

    @Test
    fun test_handle_checkJustWithHeadphonesSettings_ifPlayJustWithHeadphones_ifNoHeadphones_dontRead() {
        val notification = MockNotification.notification()

        Mockito.`when`(mockedSettings.playJustWithHeadphones).thenReturn(true)
        Mockito.`when`(mockedDeviceStatusChecker.headphonesConnected).thenReturn(false)

        notificationHandler.handle(notification)


        verify(mockedTextReader) { times(0).read(notification.text) }
    }

    @Test
    fun test_handle_checkJustWithHeadphonesSettings_ifPlayJustWithHeadphones_ifHeadphones_read() {
        val notification = MockNotification.notification()

        Mockito.`when`(mockedSettings.playJustWithHeadphones).thenReturn(true)
        Mockito.`when`(mockedDeviceStatusChecker.headphonesConnected).thenReturn(true)

        notificationHandler.handle(notification)

        verify(mockedTextReader) { times(1).read(notification.text) }
    }

    @Test
    fun test_handle_checkJustWithHeadphonesSettings_ifDontPlayJustWithHeadphones_readAlways() {
        val notification = MockNotification.notification()

        Mockito.`when`(mockedSettings.playJustWithHeadphones).thenReturn(false)

        notificationHandler.handle(notification)

        verify(mockedTextReader) { times(1).read(notification.text) }
    }

}