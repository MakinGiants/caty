package test.notificationreader.model;


import android.content.Context;

public class NotificationActor {

    private TextReader textReader;

    public NotificationActor(Context context) {
        this.textReader = new TextReader(context);
    }

    public void manageNotification(NotificationContent notificationContent) {
        textReader.read(notificationContent.getText());
    }

}
