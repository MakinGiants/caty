package com.makingiants.caty.screens.settings

import com.makingiants.caty.model.cache.Settings
import com.makingiants.caty.model.notifications.Notifier

open class SettingsPresenter(val settings: Settings, val notifier: Notifier) {
  private var view: SettingsView? = null

  fun attach(view: SettingsView) {
    this.view = view

    if (settings.notificationPermissionGranted) {
      view.setupViews()
      view.setHeadphonesToggleCheck(settings.playJustWithHeadphones)
      view.setReadNotificationsCheck(settings.readNotificationEnabled)
      view.setReadOnlyMessageNotificationsCheck(settings.readJustMessages)

      setOtherViewsEnabled(settings.readNotificationEnabled)
    } else {
      view.startWelcomeView()
      view.close()
    }
  }

  fun unAttach() {
    view = null
  }

  fun onSwitchPlayJustWithHeadphonesClick(checked: Boolean) {
    settings.playJustWithHeadphones = checked
  }

  fun onButtonTryClick(text: String) {
    notifier.notify("Test", text)
  }

  fun onSwitchReadNotificationEnabledClick(checked: Boolean) {
    settings.readNotificationEnabled = checked
    setOtherViewsEnabled(checked)
  }

  open fun setOtherViewsEnabled(enabled: Boolean) {
    view?.apply {
      setEnabledSwitchPlayJustWithHeadphones(enabled)
      setEnabledSwitchPlayJustMessageNotifications(enabled)
    }
  }
}