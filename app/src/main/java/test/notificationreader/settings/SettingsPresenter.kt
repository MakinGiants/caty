package test.notificationreader.settings

import test.notificationreader.model.cache.Settings

class SettingsPresenter {
    private var mSettings: Settings? = null
    private var mView: SettingsView? = null

    fun onCreate(view: SettingsView, settings: Settings) {
        mView = view
        mSettings = settings

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
}