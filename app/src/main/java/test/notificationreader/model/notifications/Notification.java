package test.notificationreader.model.notifications;

public class Notification {

    private String mText;

    private String mPackage;

    //<editor-fold desc="Constructors">

    public Notification(String text, String aPackage) {
        mText = text;
        mPackage = aPackage;
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

}
