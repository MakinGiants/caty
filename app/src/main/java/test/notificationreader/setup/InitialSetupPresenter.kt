package test.notificationreader.setup

import test.notificationreader.model.cache.Settings
import test.notificationreader.model.notifications.Notifier

class InitialSetupPresenter {
    private var mView: InitialSetupView? = null
    private var mNotifier: Notifier? = null
    private var mSettings: Settings? = null

    fun onCreate(view: InitialSetupView, notificationFabric: Notifier, settings: Settings) {
        mView = view
        mNotifier = notificationFabric
        mSettings = settings
    }

    fun onButtonNotificationPermissionClick() {
        mView?.startNotificationPermissionView()
    }

    fun onButtonTryClick() {
        mNotifier?.notify("Notification Test",
                "Notification test reader: This is the first try for a notification.")
    }

    fun onButtonNextClick() {
        //TODO: check if permissions where granted
        mSettings?.permissionGranted = true
        mView?.startSettingsView()
        mView?.stop()
    }
}
