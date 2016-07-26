package com.makingiants.caty.screens.settings

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.makingiants.caty.CatyApplication
import com.makingiants.caty.R
import com.makingiants.caty.screens.welcome.WelcomeActivity
import kotlinx.android.synthetic.main.settings_activity.*
import java.util.*
import javax.inject.Inject

class SettingsActivity : AppCompatActivity(), SettingsView {
  @Inject lateinit var presenter: SettingsPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.settings_activity)
    (application as CatyApplication).applicationComponent.inject(this)
  }

  override fun onResume() {
    super.onResume()
    presenter.attach(this)
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.unAttach()
  }

  override fun setupViews() {
    setTitle(R.string.settings)

    justHeadphonesSwitch.setOnCheckedChangeListener({ v, check ->
      presenter.onSwitchPlayJustWithHeadphonesClick(check)
    })

    readNotificationSwitch.setOnCheckedChangeListener { v, check ->
      presenter.onSwitchReadNotificationEnabledClick(check)
    }

    tryButton.setOnClickListener({
      val testStrings = resources.getStringArray(R.array.settings_test)
      presenter.onButtonTryClick(testStrings[Random().nextInt(testStrings.size)])
    })
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

  override fun setReadOnlyMessageNotificationsCheck(checked: Boolean) {
    justMessageNotificationsSwitch.isChecked = checked
  }

  override fun setEnabledSwitchPlayJustWithHeadphones(enabled: Boolean) {
    justHeadphonesSwitch.isEnabled = enabled
  }

  override fun setEnabledSwitchPlayJustMessageNotifications(enabled: Boolean) {
    justMessageNotificationsSwitch.isEnabled = enabled
  }

}
