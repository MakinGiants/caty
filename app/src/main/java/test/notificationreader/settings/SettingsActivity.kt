package test.notificationreader.settings

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*
import test.notificationreader.R
import test.notificationreader.model.AndroidNotificationFabric
import test.notificationreader.model.cache.Settings
import test.notificationreader.setup.InitialSetupActivity

class SettingsActivity : AppCompatActivity(), SettingsView {
    private var mPresenter: SettingsPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter = SettingsPresenter()
        mPresenter?.onCreate(this, Settings(applicationContext),
                AndroidNotificationFabric(applicationContext))
    }

    override fun initViews() {
        setContentView(R.layout.activity_settings)

        justHeadphonesSwitch.setOnCheckedChangeListener({ v, check ->
            mPresenter?.onTogglePlayJustWithHeadphonesClick(check)
        })

        tryButton.setOnClickListener({ mPresenter?.onButtonTryClick() })
    }

    override fun startSettingsView() =
            startActivity(Intent(applicationContext, InitialSetupActivity::class.java))

    override fun close() = finish()

    override fun setHeadphonesToggleCheck(checked: Boolean) =
            justHeadphonesSwitch.setChecked(checked)

}