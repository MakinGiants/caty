package com.makingiants.caty.screens.welcome

import net.paslavsky.kotlin.mockito.verifyOnce
import net.paslavsky.kotlin.mockito.verifyZeroInteractions
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import com.makingiants.caty.model.cache.Settings

class WelcomePresenterTest {
  @Mock lateinit var mockedView: WelcomeView
  @Mock lateinit var mockedSettings: Settings
  lateinit var mPresenter: WelcomePresenter

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    mPresenter = WelcomePresenter()
    mPresenter.attach(mockedView, mockedSettings)

    Mockito.reset(mockedView)
  }

  @Test
  fun test_onCreate_initViews() {
    mPresenter.attach(mockedView, mockedSettings)
    verify(mockedView).initViews()
  }

  @Test
  fun test_onButtonPermission_startNotificationPermissionView() {
    mPresenter.onButtonNotificationPermissionClick()
    verify(mockedView).startNotificationPermissionView()
  }

  @Test
  fun test_onResume_ifNotificationPermission_isGranted_startSettingsActivity() {
    Mockito.`when`(mockedSettings.notificationPermissionGranted).thenReturn(true)
    mPresenter.showSettingsIfNoPermissions()

    verifyOnce(mockedView) {
      close()
      startSettingsView()
    }
  }

  @Test
  fun test_onResume_ifNotificationPermission_notGranted_doNothing() {
    Mockito.`when`(mockedSettings.notificationPermissionGranted).thenReturn(false)
    mPresenter.showSettingsIfNoPermissions()
    verifyZeroInteractions(mockedView)
  }
}