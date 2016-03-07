package test.notificationreader.settings

import test.notificationreader.model.cache.Settings
import test.notificationreader.model.notifications.Notifier

class SettingsPresenter {
    private var mSettings: Settings? = null
    private var mView: SettingsView? = null
    private var mNotifier: Notifier? = null

    fun onCreate(view: SettingsView, settings: Settings, notificationFabric: Notifier) {
        mView = view
        mSettings = settings
        mNotifier = notificationFabric

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
        mNotifier?.notify("Notification Test",
                "Notification test reader: This is the first try for a notification.")
    }
}