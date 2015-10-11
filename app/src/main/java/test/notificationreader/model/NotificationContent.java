package test.notificationreader.model;

import android.service.notification.StatusBarNotification;
import android.view.accessibility.AccessibilityEvent;

public class NotificationContent {

    //<editor-fold desc="Attributes">

    private String mText;
    private String mPackage;

    //</editor-fold>

    //<editor-fold desc="Constructors">

    public NotificationContent(StatusBarNotification notification) {
        mText = notification.getNotification().tickerText.toString();
        mPackage = new StringBuilder(notification.getPackageName()).toString();
    }

    public NotificationContent(AccessibilityEvent event) {
        mText = getEventText(event);
        mPackage = new StringBuilder(event.getPackageName()).toString();
    }

    //</editor-fold>

    //<editor-fold desc="Getter/Setter">

    public String getText() {
        return mText;
    }

    public String getPackage() {
        return mPackage;
    }

    //</editor-fold>

    //<editor-fold desc="Others">
    private String getEventText(AccessibilityEvent event) {
        StringBuilder sb = new StringBuilder();
        for (CharSequence s : event.getText()) {
            sb.append(s);
        }
        return sb.toString();
    }

    //</editor-fold>

}
