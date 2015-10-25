package test.notificationreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityInitialSetup extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_setup);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.initial_button_permission)
    public void askPermissions() {
        String action;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            action = Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            action = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";
        } else {
            action = Settings.ACTION_ACCESSIBILITY_SETTINGS;
        }

        startActivity(new Intent(action));
    }

}