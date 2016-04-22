package com.makingiants.caty.settings

interface SettingsView {
  fun initViews()

  fun startWelcomeView()

  fun setHeadphonesToggleCheck(checked: Boolean)
  fun setReadNotificationsCheck(checked: Boolean)
  fun setReadOnlyMessageNotificationsCheck(checked: Boolean)

  fun close()

  fun setEnabledSwitchPlayJustWithHeadphones(enabled: Boolean)
  fun setEnabledSwitchPlayJustMessageNotifications(enabled: Boolean)
}
