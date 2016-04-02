package test.notificationreader.welcome

import test.notificationreader.model.cache.Settings

class WelcomePresenter {
  private var mView: WelcomeView? = null
  private var mSettings: Settings? = null

  fun onCreate(view: WelcomeView, settings: Settings) {
    mView = view
    mSettings = settings
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
