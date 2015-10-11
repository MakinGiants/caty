package test.notificationreader;

import android.app.Activity;
import android.content.Intent;
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
        Intent intent;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        } else {
            intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        }
        startActivity(intent);
    }

}