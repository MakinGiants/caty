package test.notificationreader.settings

interface SettingsView {
    fun setHeadphonesToggleCheck(checked: Boolean)

    fun initViews()

    fun startSettingsView()

    fun close()
}
