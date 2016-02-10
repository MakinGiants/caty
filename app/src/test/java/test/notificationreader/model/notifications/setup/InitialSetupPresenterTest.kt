package test.notificationreader.model.notifications.setup

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import test.notificationreader.model.NotificationFabric
import test.notificationreader.setup.InitialSetupPresenter
import test.notificationreader.setup.InitialSetupView

class InitialSetupPresenterTest {
    @Mock internal var mockedView: InitialSetupView? = null
    @Mock internal var mockedNotificationFabric: NotificationFabric? = null
    internal var mPresenter: InitialSetupPresenter? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        mPresenter = InitialSetupPresenter()
        mPresenter!!.onCreate(mockedView!!, mockedNotificationFabric!!)
    }

    @Test
    fun test_onButtonTry_startNotification2() {
        val title = "Notification Test"
        val text = "Notification test reader: This is the first try for a notification."

        mPresenter!!.onButtonTryClick()

        verify(mockedNotificationFabric!!).notify(title, text)
    }

    @Test
    fun test_onButtonPermission_startNotificationPermissionView() {
        mPresenter!!.onButtonNotificationPermissionClick()

        verify(mockedView!!).startNotificationPermissionView()
    }
}