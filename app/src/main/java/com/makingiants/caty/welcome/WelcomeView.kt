package com.makingiants.caty.welcome

interface WelcomeView {
  fun initViews()

  fun startNotificationPermissionView()
  fun startSettingsView()

  fun close()
}
