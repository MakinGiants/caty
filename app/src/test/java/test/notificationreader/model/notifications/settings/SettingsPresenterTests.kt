package test.notificationreader.model.notifications.settings

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import test.notificationreader.model.cache.Settings
import test.notificationreader.settings.SettingsPresenter
import test.notificationreader.settings.SettingsView

class SettingsPresenterTests {
    @Mock lateinit var mockedView: SettingsView
    @Mock lateinit var mockedSettings: Settings
    lateinit var presenter: SettingsPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SettingsPresenter()
    }

    @Test
    fun test_onCreate_updateGui() {
        Mockito.`when`(mockedSettings.playJustWithHeadphones).thenReturn(true)
        presenter.onCreate(mockedView, mockedSettings)

        Mockito.verify(mockedView).initViews()
        Mockito.verify(mockedView).setHeadphonesToggleCheck(true)
    }

    @Test
    fun test_onTogglePlayJustWithHeadphonesClick_updateGui() {
        Mockito.`when`(mockedSettings.playJustWithHeadphones).thenReturn(true)
        presenter.onCreate(mockedView, mockedSettings)

        presenter.onTogglePlayJustWithHeadphonesClick(false)
        Mockito.verify(mockedView, times(2)).setHeadphonesToggleCheck(false)
    }
}
