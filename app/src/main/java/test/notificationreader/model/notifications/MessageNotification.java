package test.notificationreader.model.notifications;

import android.support.annotation.Nullable;

public class MessageNotification extends Notification {

    private String mName;
    private String mMessage;

    public MessageNotification(String text, @Nullable String aPackage) {
        super(text, aPackage);

        String[] splitedText = text.split(":");
        mName = splitedText[0].trim();
        mMessage = splitedText[1].trim();
    }

    public String getName() {
        return mName;
    }

    public String getMessage() {
        return mMessage;
    }
}
