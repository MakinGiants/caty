package test.notificationreader.services;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import test.notificationreader.model.TextReader;
import test.notificationreader.model.notifications.Notification;
import test.notificationreader.model.notifications.NotificationActor;
import test.notificationreader.model.notifications.NotificationFactory;

/**
 * Used to listen notifications for SDK >= 18
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class NotificationService extends NotificationListenerService {

    NotificationActor mNotificationActor;

    @Override
    public IBinder onBind(Intent intent) {
        mNotificationActor = new NotificationActor(new TextReader(getApplicationContext()));
        return super.onBind(intent);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        String text = sbn.getNotification().tickerText.toString();
        String aPackage = sbn.getPackageName();

        Notification notification = NotificationFactory.build(text, aPackage);
        mNotificationActor.manageNotification(notification);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
    }
}
