package test.notificationreader.setup

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.initial_setup_activity.*
import test.notificationreader.R
import test.notificationreader.model.cache.Settings
import test.notificationreader.settings.SettingsActivity
import android.provider.Settings as ProviderSettings

class InitialSetupActivity : AppCompatActivity(), InitialSetupView {
  private var mPresenter: InitialSetupPresenter? = null

  public override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.initial_setup_activity)

    mPresenter = InitialSetupPresenter()
    mPresenter?.onCreate(this, Settings(applicationContext))

    permissionButton.setOnClickListener { mPresenter?.onButtonNotificationPermissionClick() }
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
