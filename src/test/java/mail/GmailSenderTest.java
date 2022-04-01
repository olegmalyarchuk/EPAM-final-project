package mail;

import com.example.conference.entity.User;
import com.example.conference.mail.GmailSender;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GmailSenderTest {
    @Test
    public void checkWelcome() {
        boolean result = GmailSender.sendWelcome("test@gmail.com", "test", "test", "en");
        Assert.assertTrue(result);
        result = GmailSender.sendWelcome("@g", "test", "test", "en");
        Assert.assertFalse(result);

    }
    @Test
    public void checkEventWelcome() {
        boolean result = GmailSender.sendEventWelcome("test@gmail.com", "test", "test", "test", LocalDateTime.now(), "en");
        Assert.assertTrue(result);
        result = GmailSender.sendEventWelcome("@g", "test", "test", "test", LocalDateTime.now(), "en");
        Assert.assertFalse(result);
    }

    @Test
    public void checkEventChange() {
        List<User> list = new ArrayList<>();
        User test = new User();
        test.setUser_email("test@gmail.com");
        boolean result = GmailSender.sendEventChange(list, "test", "en");
        Assert.assertTrue(result);
    }

}
