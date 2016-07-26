package com.makingiants.caty.screens.settings

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
      view.setReadOnlyMessageNotificationsCheck(settings.readJustMessages)

      setOtherViewsEnabled(settings.readNotificationEnabled)
    } else {
      view.startWelcomeView()
      view.close()
    }
  }

  fun onSwitchPlayJustWithHeadphonesClick(checked: Boolean) {
    mSettings?.playJustWithHeadphones = checked
  }

  fun onButtonTryClick(text: String) {
    mNotifier?.notify("Test", text)
  }

  fun onSwitchReadNotificationEnabledClick(checked: Boolean) {
    mSettings?.readNotificationEnabled = checked
    setOtherViewsEnabled(checked)
  }

  open fun setOtherViewsEnabled(enabled: Boolean) {
    mView?.apply {
      setEnabledSwitchPlayJustWithHeadphones(enabled)
      setEnabledSwitchPlayJustMessageNotifications(enabled)
    }
  }
}