package test.notificationreader.welcome

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.welcome_activity.*
import test.notificationreader.R
import test.notificationreader.model.cache.Settings
import test.notificationreader.settings.SettingsActivity
import android.provider.Settings as ProviderSettings

class WelcomeActivity : AppCompatActivity(), WelcomeView {
  private var mPresenter: WelcomePresenter? = null

  public override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.welcome_activity)

    mPresenter = WelcomePresenter()
    mPresenter?.onCreate(this, Settings(applicationContext))

    permissionButton.setOnClickListener { mPresenter?.onButtonNotificationPermissionClick() }
  }

  override fun onResume() {
    super.onResume()
    mPresenter?.onResume()
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
