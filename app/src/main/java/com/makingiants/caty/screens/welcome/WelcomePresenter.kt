package com.makingiants.caty.screens.welcome

import com.makingiants.caty.model.cache.Settings

class WelcomePresenter(val settings: Settings) {
  private var view: WelcomeView? = null

  fun attach(view: WelcomeView) {
    this.view = view
    view.setupViews()
    showSettingsIfNoPermissions()
  }

  fun onButtonNotificationPermissionClick() {
    view?.startNotificationPermissionView()
  }

  fun showSettingsIfNoPermissions() {
    if (settings.notificationPermissionGranted) {
      view?.apply {
        close()
        startSettingsView()
      }
    }
  }

  fun unAttach() {
    view = null
  }
}
