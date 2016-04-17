package com.makingiants.caty.settings

import com.makingiants.caty.model.cache.Settings
import com.makingiants.caty.model.notifications.Notifier

open class SettingsPresenter {
  private var mSettings: Settings? = null
  private var mView: SettingsView? = null
  private var mNotifier: Notifier? = null

  fun onCreate(view: SettingsView, settings: Settings, notificationFabric: Notifier) {
    mView = view
    mSettings = settings
    mNotifier = notificationFabric

    if (mSettings?.notificationPermissionGranted ?: false) {
      view.initViews()
      view.setHeadphonesToggleCheck(settings.playJustWithHeadphones)
      view.setReadNotificationsCheck(settings.readNotificationEnabled)

      setOtherViewsEnabled(settings.readNotificationEnabled)
    } else {
      view.startWelcomeView()
      view.close()
    }
  }

  fun onSwitchPlayJustWithHeadphonesClick(checked: Boolean) {
    mSettings?.playJustWithHeadphones = checked
  }

  fun onButtonTryClick() {
    mNotifier?.notify("Notification Test", "Caty: I think you are so great.")
  }

  fun onSwitchReadNotificationEnabledClick(checked: Boolean) {
    mSettings?.readNotificationEnabled = checked
    setOtherViewsEnabled(checked)
  }

  open fun setOtherViewsEnabled(enabled: Boolean) {
    mView?.setEnabledSwitchPlayJustWithHeadphones(enabled)
  }
}