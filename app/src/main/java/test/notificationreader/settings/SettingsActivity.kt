package test.notificationreader.settings

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.settings_activity.*
import test.notificationreader.R
import test.notificationreader.model.cache.Settings
import test.notificationreader.model.notifications.Notifier
import test.notificationreader.setup.InitialSetupActivity

class SettingsActivity : AppCompatActivity(), SettingsView {
    private var mPresenter: SettingsPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter = SettingsPresenter()
        mPresenter?.onCreate(this, Settings(applicationContext), Notifier(applicationContext))
    }

    override fun initViews() {
        setContentView(R.layout.settings_activity)

        justHeadphonesSwitch.setOnCheckedChangeListener({ v, check ->
            mPresenter?.onSwitchPlayJustWithHeadphonesClick(check)
        })

        readNotificationSwitch.setOnCheckedChangeListener { v, check ->
            mPresenter?.onSwitchReadNotificationEnabledClick(check)
        }

        tryButton.setOnClickListener({ mPresenter?.onButtonTryClick() })
    }

    override fun startSettingsView() =
            startActivity(Intent(applicationContext, InitialSetupActivity::class.java))

    override fun close() = finish()

    override fun setHeadphonesToggleCheck(checked: Boolean) =
            justHeadphonesSwitch.setChecked(checked)

    override fun setReadNotificationsCheck(checked: Boolean) =
            readNotificationSwitch.setChecked(checked)

    override fun setEnabledSwitchPlayJustWithHeadphones(enabled: Boolean) =
            justHeadphonesSwitch.setEnabled(enabled)

    override fun setEnabledButtonTry(enabled: Boolean) =
            tryButton.setEnabled(enabled)

}
