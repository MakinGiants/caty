package com.makingiants.caty.settings

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.makingiants.caty.R
import com.makingiants.caty.model.cache.Settings
import com.makingiants.caty.model.notifications.Notifier
import com.makingiants.caty.welcome.WelcomeActivity
import kotlinx.android.synthetic.main.settings_activity.*

class SettingsActivity : AppCompatActivity(), SettingsView {
  private var mPresenter: SettingsPresenter? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    mPresenter = SettingsPresenter()
    mPresenter?.onCreate(this, Settings(applicationContext), Notifier(applicationContext))
  }

  override fun initViews() {
    setContentView(R.layout.settings_activity)
    setTitle(R.string.settings)

    justHeadphonesSwitch.setOnCheckedChangeListener({ v, check ->
      mPresenter?.onSwitchPlayJustWithHeadphonesClick(check)
    })

    readNotificationSwitch.setOnCheckedChangeListener { v, check ->
      mPresenter?.onSwitchReadNotificationEnabledClick(check)
    }

    tryButton.setOnClickListener({ mPresenter?.onButtonTryClick() })
  }

  override fun startWelcomeView() =
      startActivity(Intent(applicationContext, WelcomeActivity::class.java))

  override fun close() = finish()

  override fun setHeadphonesToggleCheck(checked: Boolean) {
    justHeadphonesSwitch.isChecked = checked
  }

  override fun setReadNotificationsCheck(checked: Boolean) {
    readNotificationSwitch.isChecked = checked
  }

  override fun setEnabledSwitchPlayJustWithHeadphones(enabled: Boolean) {
    justHeadphonesSwitch.isEnabled = enabled
  }

}
