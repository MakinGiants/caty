package com.makingiants.caty.screens.welcome

interface WelcomeView {
  fun initViews()

  fun startNotificationPermissionView()
  fun startSettingsView()

  fun close()
}
