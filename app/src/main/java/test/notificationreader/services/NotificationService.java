package test.notificationreader.services;

import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import test.notificationreader.model.NotificationActor;
import test.notificationreader.model.NotificationContent;

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
