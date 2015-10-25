package test.notificationreader.services;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityEvent;

import test.notificationreader.model.TextReader;
import test.notificationreader.model.notifications.Notification;
import test.notificationreader.model.notifications.NotificationActor;
import test.notificationreader.model.notifications.NotificationFactory;

public class AccessibilityListenerService extends AccessibilityService {

    NotificationActor mNotificationActor;

    @Override
    public void onServiceConnected() {
        super.onServiceConnected();
        mNotificationActor = new NotificationActor(new TextReader(getApplicationContext()));

        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_AUDIBLE;
        info.notificationTimeout = 100;
        setServiceInfo(info);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        String text = getEventText(event);
        String aPackage = String.valueOf(event.getPackageName());

        Notification notification = NotificationFactory.build(text, aPackage);
        mNotificationActor.manageNotification(notification);
    }

    @Override
    public void onInterrupt() {

    }

    public static String getEventText(AccessibilityEvent event) {
        StringBuilder sb = new StringBuilder();
        for (CharSequence s : event.getText()) {
            sb.append(s);
        }
        return sb.toString();
    }
}