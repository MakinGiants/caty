package test.notificationreader.setup

import test.notificationreader.model.NotificationFabric

class InitialSetupPresenter {
    private var mView: InitialSetupView? = null
    private var mNotificationFabric: NotificationFabric? = null

    fun onCreate(view: InitialSetupView, notificationFabric: NotificationFabric) {
        mView = view
        mNotificationFabric = notificationFabric
    }

    fun onButtonNotificationPermissionClick() {
        mView?.startNotificationPermissionView()
    }

    fun onButtonTryClick() {
        mNotificationFabric?.notify("Notification Test",
                "Notification test reader: This is the first try for a notification.")
    }
}
