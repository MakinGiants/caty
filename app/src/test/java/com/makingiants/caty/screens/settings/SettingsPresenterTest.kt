package com.makingiants.caty.screens.settings

import com.makingiants.caty.model.cache.Settings
import com.makingiants.caty.model.notifications.Notifier
import net.paslavsky.kotlin.mockito.spy
import net.paslavsky.kotlin.mockito.verifyOnce
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.anyString
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SettingsPresenterTest {
  @Mock lateinit var mockedView: SettingsView
  @Mock lateinit var mockedSettings: Settings
  @Mock lateinit var mockedNotificationFabric: Notifier
  lateinit var presenter: SettingsPresenter

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)

    presenter = SettingsPresenter(mockedSettings, mockedNotificationFabric)
    presenter.attach(mockedView)

    Mockito.reset(mockedView)
  }

  fun test_onCreate_updateGui(checked: Boolean) {
    val spiedPresenter = spy(presenter)

    Mockito.`when`(mockedSettings.playJustWithHeadphones).thenReturn(checked)
    Mockito.`when`(mockedSettings.notificationPermissionGranted).thenReturn(true)
    Mockito.`when`(mockedSettings.readNotificationEnabled).thenReturn(checked)
    Mockito.`when`(mockedSettings.readJustMessages).thenReturn(checked)

    spiedPresenter.attach(mockedView)

    verifyOnce(mockedView) {
      setupViews()
      setHeadphonesToggleCheck(checked)
      setReadNotificationsCheck(checked)
      setReadOnlyMessageNotificationsCheck(checked)
    }
    verify(spiedPresenter).setOtherViewsEnabled(checked)
  }

  @Test
  fun test_onCreate_updateGui_withTrue() {
    test_onCreate_updateGui(true);
  }

  @Test
  fun test_onCreate_updateGui_withFalse() {
    test_onCreate_updateGui(false);
  }

  @Test
  fun test_onCreate_ifPermissionsNOTGranted_startWelcomeView() {
    Mockito.`when`(mockedSettings.notificationPermissionGranted).thenReturn(false)

    presenter.attach(mockedView)

    verifyOnce(mockedView) {
      startWelcomeView()
      close()
    }
  }

  @Test
  fun test_onSwitchPlayJustWithHeadphonesClick_updateSettings_andDisableOthers() {
    presenter.onSwitchPlayJustWithHeadphonesClick(false)
    verify(mockedSettings).playJustWithHeadphones = false
  }

  fun test_onSwitchReadNotificationEnabledClick_updateSettings_andEnableOthers(enabled: Boolean) {
    val spiedPresenter = spy(presenter)
    spiedPresenter.onSwitchReadNotificationEnabledClick(enabled)

    verify(mockedSettings).readNotificationEnabled = enabled
    verify(spiedPresenter).setOtherViewsEnabled(enabled)
  }

  @Test
  fun test_onSwitchReadNotificationEnabledClick_updateSettings_andEnableOthers_withFalse() {
    test_onSwitchReadNotificationEnabledClick_updateSettings_andEnableOthers(false)
  }

  @Test
  fun test_onSwitchReadNotificationEnabledClick_updateSettings_andEnableOthers_withTrue() {
    test_onSwitchReadNotificationEnabledClick_updateSettings_andEnableOthers(true)
  }

  @Test
  fun test_onButtonTryClick_notify() {
    presenter.onButtonTryClick("Test")
    verify(mockedNotificationFabric).notify(anyString(), anyString())
  }

  fun test_setOtherViewsEnabled_updateViews(enabled: Boolean) {
    val spiedPresenter = spy(presenter)

    spiedPresenter.setOtherViewsEnabled(enabled)

    verifyOnce(mockedView) {
      setEnabledSwitchPlayJustWithHeadphones(enabled)
      setEnabledSwitchPlayJustMessageNotifications(enabled)
    }
  }

  @Test
  fun test_setOtherViewsEnabled_updateViews_withTrue() {
    test_setOtherViewsEnabled_updateViews(true)
  }

  @Test
  fun test_setOtherViewsEnabled_updateViews_withFalse() {
    test_setOtherViewsEnabled_updateViews(false)
  }
}
