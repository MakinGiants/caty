package test.notificationreader.services;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityEvent;

import test.notificationreader.model.NotificationActor;
import test.notificationreader.model.NotificationContent;

public class AccessibilityListenerService extends AccessibilityService {

    //<editor-fold desc="Attributes">

    NotificationActor mNotificationActor;

    //</editor-fold>

    //<editor-fold desc="AccessibilityService Overrides">

    @Override
    public void onServiceConnected() {
        super.onServiceConnected();
        mNotificationActor = new NotificationActor(getApplicationContext());

        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_AUDIBLE;
        info.notificationTimeout = 100;
        setServiceInfo(info);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        NotificationContent notificationContent = new NotificationContent(event);
        mNotificationActor.manageNotification(notificationContent);
    }

    @Override
    public void onInterrupt() {

    }

    //</editor-fold>

}