package test.notificationreader.services;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import test.notificationreader.model.NotificationActor;
import test.notificationreader.model.NotificationContent;

/**
 * Used to listen notifications for SDK >= 18
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class NotificationService extends NotificationListenerService {

    NotificationActor mNotificationActor;

    @Override
    public IBinder onBind(Intent intent) {
        mNotificationActor = new NotificationActor(getApplicationContext());
        return super.onBind(intent);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        NotificationContent notificationContent = new NotificationContent(sbn);
        mNotificationActor.manageNotification(notificationContent);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
    }
}
