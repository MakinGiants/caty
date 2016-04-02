package test.notificationreader.settings

interface SettingsView {
  fun initViews()

  fun startSettingsView()

  fun setHeadphonesToggleCheck(checked: Boolean)
  fun setReadNotificationsCheck(checked: Boolean)

  fun close()

  fun setEnabledSwitchPlayJustWithHeadphones(enabled: Boolean)
}
