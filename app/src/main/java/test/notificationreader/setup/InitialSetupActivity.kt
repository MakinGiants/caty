package test.notificationreader.setup

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import test.notificationreader.R
import test.notificationreader.model.AndroidNotificationFabric
import test.notificationreader.model.cache.Settings
import test.notificationreader.settings.SettingsActivity
import android.provider.Settings as ProviderSettings

class InitialSetupActivity : AppCompatActivity(), InitialSetupView {
    private var mPresenter: InitialSetupPresenter? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_setup)
        ButterKnife.bind(this)

        mPresenter = InitialSetupPresenter()
        mPresenter?.onCreate(this, AndroidNotificationFabric(applicationContext),
                Settings(applicationContext))
    }

    override fun startSettingsView() =
            startActivity(Intent(applicationContext, SettingsActivity::class.java))

    @OnClick(R.id.initial_button_next)
    fun next() {
        mPresenter?.onButtonNextClick()
    }

    @OnClick(R.id.initial_button_permission)
    fun askPermissions() {
        mPresenter?.onButtonNotificationPermissionClick()
    }

    @OnClick(R.id.initial_button_try)
    fun createNotification() {
        mPresenter?.onButtonTryClick()
    }

    //<editor-fold desc="InitialSetupView">
    override fun startNotificationPermissionView() {
        val action: String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            action = ProviderSettings.ACTION_NOTIFICATION_LISTENER_SETTINGS
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            action = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"
        } else {
            action = ProviderSettings.ACTION_ACCESSIBILITY_SETTINGS
        }

        startActivity(Intent(action))
    }

    override fun stop() {
        finish()
    }
    //</editor-fold>
}
