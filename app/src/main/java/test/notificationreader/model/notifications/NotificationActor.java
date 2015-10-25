package test.notificationreader.model.notifications;


import test.notificationreader.model.TextReader;

public class NotificationActor {

    private TextReader textReader;

    public NotificationActor(TextReader textReader) {
        this.textReader = textReader;
    }

    public void manageNotification(Notification notification) {
        textReader.read(notification.getText());
    }

}
