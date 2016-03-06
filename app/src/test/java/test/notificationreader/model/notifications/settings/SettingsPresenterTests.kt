package test.notificationreader.model.notifications.settings

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import test.notificationreader.model.NotificationFabric
import test.notificationreader.model.cache.Settings
import test.notificationreader.settings.SettingsPresenter
import test.notificationreader.settings.SettingsView
import net.paslavsky.kotlin.mockito.verify as kNotify

class SettingsPresenterTests {
    @Mock lateinit var mockedView: SettingsView
    @Mock lateinit var mockedSettings: Settings
    @Mock lateinit var mockedNotificationFabric: NotificationFabric
    lateinit var presenter: SettingsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = SettingsPresenter()
        presenter.onCreate(mockedView, mockedSettings, mockedNotificationFabric)

        Mockito.reset(mockedView)
    }

    @Test
    fun test_onCreate_ifPermissionsGranted_updateGui() {
        Mockito.`when`(mockedSettings.playJustWithHeadphones).thenReturn(true)
        Mockito.`when`(mockedSettings.permissionGranted).thenReturn(true)
        presenter.onCreate(mockedView, mockedSettings, mockedNotificationFabric)

        verify(mockedView).initViews()
        verify(mockedView).setHeadphonesToggleCheck(true)
    }

    @Test
    fun test_onCreate_ifPermissionsNOTGranted_startSetupView() {
        Mockito.`when`(mockedSettings.playJustWithHeadphones).thenReturn(true)
        Mockito.`when`(mockedSettings.permissionGranted).thenReturn(false)
        presenter.onCreate(mockedView, mockedSettings, mockedNotificationFabric)

        verify(mockedView).startSettingsView()
        verify(mockedView).close()
    }

    @Test
    fun test_onTogglePlayJustWithHeadphonesClick_updateGui() {
        presenter.onTogglePlayJustWithHeadphonesClick(false)
        verify(mockedSettings).playJustWithHeadphones = false
    }

    @Test
    fun test_onButtonTryClick_notify() {
        presenter.onButtonTryClick()
        kNotify(mockedNotificationFabric) { times(1).notify(anyString(), anyString()) }
    }
}
