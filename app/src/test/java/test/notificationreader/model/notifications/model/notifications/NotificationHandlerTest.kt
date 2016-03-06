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


    fun test_handle_checkJustWithHeadphonesSettings(playJustWithHeadphones: Boolean, headphonesConnected: Boolean) {
        val notification = MockNotification.notification()

        Mockito.`when`(mockedDeviceStatusChecker.headphonesConnected).thenReturn(headphonesConnected)
        Mockito.`when`(mockedSettings.playJustWithHeadphones).thenReturn(playJustWithHeadphones)

        notificationHandler.handle(notification)

        val times = if(playJustWithHeadphones && headphonesConnected) 1 else 0

        verify(mockedTextReader) { times(times).read(notification.text) }
    }

    @Test
    fun test_handle_checkJustWithHeadphonesSettings_ifPlayJustWithHeadphones_ifNoHeadphones_dontRead(){
        test_handle_checkJustWithHeadphonesSettings(true, false)
    }

    @Test
    fun test_handle_checkJustWithHeadphonesSettings_ifPlayJustWithHeadphones_ifHeadphones_read(){
        test_handle_checkJustWithHeadphonesSettings(true, true)
    }

    @Test
    fun test_handle_checkJustWithHeadphonesSettings_ifDontPlayJustWithHeadphones_readAlways(){
        test_handle_checkJustWithHeadphonesSettings(false, true)
        test_handle_checkJustWithHeadphonesSettings(false, false)
    }


}