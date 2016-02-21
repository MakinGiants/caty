package test.notificationreader.setup

import test.notificationreader.model.NotificationFabric
import test.notificationreader.model.cache.Settings

class InitialSetupPresenter {
    private var mView: InitialSetupView? = null
    private var mNotificationFabric: NotificationFabric? = null
    private var mSettings: Settings? = null

    fun onCreate(view: InitialSetupView, notificationFabric: NotificationFabric, settings: Settings) {
        mView = view
        mNotificationFabric = notificationFabric
        mSettings = settings
    }

    fun onButtonNotificationPermissionClick() {
        mView?.startNotificationPermissionView()
    }

    fun onButtonTryClick() {
        mNotificationFabric?.notify("Notification Test",
                "Notification test reader: This is the first try for a notification.")
    }

    fun onButtonNextClick() {
        //TODO: check if permissions where granted
        mSettings?.permissionGranted = true
        mView?.startSettingsView()
        mView?.stop()
    }
}
