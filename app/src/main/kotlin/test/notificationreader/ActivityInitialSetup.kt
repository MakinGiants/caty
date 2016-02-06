package test.notificationreader

import android.app.Activity
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.NotificationCompat
import butterknife.ButterKnife
import butterknife.OnClick


class ActivityInitialSetup : Activity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_setup)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.initial_button_permission)
    fun askPermissions() {
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

    @OnClick(R.id.initial_button_try)
    fun createNotification() {
        val mBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Notification Test")
                .setContentText("Notification test reader: This is the first try for a notification.")

        val mNotifyMgr = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        mNotifyMgr.notify(1, mBuilder.build());
    }
}