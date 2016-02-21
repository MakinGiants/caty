package test.notificationreader.model.notifications.setup

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import test.notificationreader.model.NotificationFabric
import test.notificationreader.model.cache.Settings
import test.notificationreader.setup.InitialSetupPresenter
import test.notificationreader.setup.InitialSetupView

class InitialSetupPresenterTest {
    @Mock lateinit var mockedView: InitialSetupView
    @Mock lateinit var mockedNotificationFabric: NotificationFabric
    @Mock lateinit var mockedSettings: Settings
    lateinit var mPresenter: InitialSetupPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        mPresenter = InitialSetupPresenter()
        mPresenter.onCreate(mockedView, mockedNotificationFabric, mockedSettings)
    }

    @Test
    fun test_onButtonNext_startSettingsView() {
        mPresenter.onButtonNextClick()

        verify(mockedView).startSettingsView()
        verify(mockedView).stop()
        verify(mockedSettings).permissionGranted = true
    }

    @Test
    fun test_onButtonTry_startNotification() {
        val title = "Notification Test"
        val text = "Notification test reader: This is the first try for a notification."

        mPresenter.onButtonTryClick()

        verify(mockedNotificationFabric).notify(title, text)
    }

    @Test
    fun test_onButtonPermission_startNotificationPermissionView() {
        mPresenter.onButtonNotificationPermissionClick()
        verify(mockedView).startNotificationPermissionView()
    }
}