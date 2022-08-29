package jimwu.itest.portal;

import jimwu.itest.portal.service.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@SpringBootTest
public class EmailSenderServiceTest {
    /*
    @Autowired
    EmailSenderService emailSenderService;

    @Test
    public void sendEmailTest() throws MessagingException, UnsupportedEncodingException {
        String mail = "jimwu0903@gmail.com";
        String link = "https://www.google.com/";
        emailSenderService.sendEmail(mail,link);
    }
     */
}
