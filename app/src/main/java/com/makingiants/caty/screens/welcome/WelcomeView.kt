package com.makingiants.caty.screens.welcome

interface WelcomeView {
  fun setupViews()

  fun startNotificationPermissionView()
  fun startSettingsView()

  fun close()
}
