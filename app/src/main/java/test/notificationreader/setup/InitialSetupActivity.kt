package test.notificationreader.setup

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import butterknife.ButterKnife
import butterknife.OnClick
import test.notificationreader.R
import test.notificationreader.model.AndroidNotificationFabric

class InitialSetupActivity : Activity(), InitialSetupView {
    private var mPresenter: InitialSetupPresenter? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_setup)
        ButterKnife.bind(this)

        mPresenter = InitialSetupPresenter()
        mPresenter?.onCreate(this, AndroidNotificationFabric(applicationContext))
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
            action = Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            action = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"
        } else {
            action = Settings.ACTION_ACCESSIBILITY_SETTINGS
        }

        startActivity(Intent(action))
    }
    //</editor-fold>
}

