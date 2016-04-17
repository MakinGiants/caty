package com.makingiants.caty.welcome

import com.makingiants.caty.model.cache.Settings

class WelcomePresenter {
  private var mView: WelcomeView? = null
  private var mSettings: Settings? = null

  fun onCreate(view: WelcomeView, settings: Settings) {
    mView = view
    mSettings = settings
    mView?.initViews()
  }

  fun onButtonNotificationPermissionClick() {
    mView?.startNotificationPermissionView()
  }

  fun onResume() {
    if (mSettings?.notificationPermissionGranted ?: false) {
      mView?.apply {
        close()
        startSettingsView()
      }
    }
  }
}
