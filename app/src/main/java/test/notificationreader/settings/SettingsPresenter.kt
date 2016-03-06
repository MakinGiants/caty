package test.notificationreader.settings

import test.notificationreader.model.NotificationFabric
import test.notificationreader.model.cache.Settings

class SettingsPresenter {
    private var mSettings: Settings? = null
    private var mView: SettingsView? = null
    private var mNotificationFabric: NotificationFabric? = null

    fun onCreate(view: SettingsView, settings: Settings, notificationFabric: NotificationFabric) {
        mView = view
        mSettings = settings
        mNotificationFabric = notificationFabric

        if (mSettings?.permissionGranted ?: false) {
            view.initViews()
            view.setHeadphonesToggleCheck(settings.playJustWithHeadphones)
        } else {
            view.startSettingsView()
            view.close()
        }
    }

    fun onTogglePlayJustWithHeadphonesClick(checked: Boolean) {
        mSettings?.playJustWithHeadphones = checked
    }

    fun onButtonTryClick() {
        mNotificationFabric?.notify("Notification Test",
                "Notification test reader: This is the first try for a notification.")
    }
}