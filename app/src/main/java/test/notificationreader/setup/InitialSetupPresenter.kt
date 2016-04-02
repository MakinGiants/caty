package test.notificationreader.setup

import test.notificationreader.model.cache.Settings

class InitialSetupPresenter {
    private var mView: InitialSetupView? = null
    private var mSettings: Settings? = null

    fun onCreate(view: InitialSetupView, settings: Settings) {
        mView = view
        mSettings = settings
    }

    fun onButtonNotificationPermissionClick() {
        mView?.startNotificationPermissionView()
    }

    fun onResume() {
        if (mSettings?.notificationPermissionGranted ?: false) {
            mView?.apply {
                close()
                startSettingsView()
            }
        }
    }
}
