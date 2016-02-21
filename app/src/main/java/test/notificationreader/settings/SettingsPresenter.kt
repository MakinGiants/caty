package test.notificationreader.settings

import test.notificationreader.model.cache.Settings

class SettingsPresenter {
    private var mSettings: Settings? = null
    private var mView: SettingsView? = null

    fun onCreate(view: SettingsView, settings: Settings) {
        mView = view
        mSettings = settings

        view.initViews()
        view.setHeadphonesToggleCheck(settings.playJustWithHeadphones)
    }

    fun onTogglePlayJustWithHeadphonesClick(checked: Boolean) {
        mSettings?.playJustWithHeadphones = checked
    }
}