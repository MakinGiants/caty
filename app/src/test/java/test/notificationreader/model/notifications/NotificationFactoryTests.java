package test.notificationreader.model.notifications;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import test.notificationreader.model.TextReader;

import static org.assertj.core.api.Assertions.assertThat;


public class NotificationFactoryTests {

    @Mock
    TextReader mTextReader;
    NotificationActor mActor;

    @Before
    public void setupMocks() {
        MockitoAnnotations.initMocks(this);
        mActor = new NotificationActor(mTextReader);
    }

    @Test
    public void buildMessageNotification() {
        Notification notification = NotificationFactory.INSTANCE.build("Maria: hello Daniel", "facebook.messenger");

        assertThat(notification).isInstanceOf(MessageNotification.class);


        MessageNotification messageNotification = (MessageNotification) notification;
        assertThat(messageNotification.getName()).isEqualTo("Maria");
        assertThat(messageNotification.getMessage()).isEqualTo("hello Daniel");
    }

    @Test
    public void buildSystemNotification() {
        Notification notification = NotificationFactory.INSTANCE.build("Something happened with phone", "android.vending");
        assertThat(notification).isInstanceOf(SystemNotification.class);
    }

    @Test
    public void buildOtherNotification() {
        Notification notification = NotificationFactory.INSTANCE.build("Maria hello Daniel", "facebook.messenger");

        assertThat(notification).isInstanceOf(Notification.class);
        assertThat(notification).isNotInstanceOf(MessageNotification.class);
        assertThat(notification).isNotInstanceOf(SystemNotification.class);
    }

}
