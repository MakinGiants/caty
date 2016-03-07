package test.notificationreader.setup

interface InitialSetupView {
    fun startNotificationPermissionView()

    fun startSettingsView()

    fun stop()
}
