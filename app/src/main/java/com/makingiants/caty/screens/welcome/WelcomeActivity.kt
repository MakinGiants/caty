package com.makingiants.caty.screens.welcome

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.makingiants.caty.CatyApplication
import com.makingiants.caty.R
import com.makingiants.caty.screens.settings.SettingsActivity
import kotlinx.android.synthetic.main.welcome_activity.*
import javax.inject.Inject
import android.provider.Settings as ProviderSettings

class WelcomeActivity : AppCompatActivity(), WelcomeView {
  @Inject lateinit var presenter: WelcomePresenter

  public override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.welcome_activity)
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
    setTitle(R.string.welcome)

    permissionButton.setOnClickListener { presenter.onButtonNotificationPermissionClick() }
  }

  override fun startSettingsView() =
      startActivity(Intent(applicationContext, SettingsActivity::class.java))

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

  override fun close() = finish()
  //</editor-fold>

}
