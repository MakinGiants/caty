package test.notificationreader.model.notifications;

import android.support.annotation.Nullable;

public class SystemNotification extends Notification {

    public SystemNotification(String text, @Nullable String aPackage) {
        super(text, aPackage);
    }

}
