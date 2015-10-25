package test.notificationreader.model.notifications;

public class NotificationFactory {

    public static Notification build(String text, String aPackage) {
        if (text.contains(":")) {
            return new MessageNotification(text, aPackage);
        } else if (aPackage.contains("android")) {
            return new SystemNotification(text, aPackage);
        }

        return new Notification(text, aPackage);
    }

}
